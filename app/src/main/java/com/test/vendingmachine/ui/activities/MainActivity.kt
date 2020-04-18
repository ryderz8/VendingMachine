package com.test.vendingmachine.ui.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.test.core.base.BaseActivity
import com.test.vendingmachine.BR
import com.test.vendingmachine.R
import com.test.vendingmachine.data.di.DependencyProvider
import com.test.vendingmachine.databinding.ActivityMainBinding
import com.test.vendingmachine.utilities.Constants
import com.test.vendingmachine.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val ZXING_CAMERA_PERMISSION = 1
    private var mClss: Class<*>? = null

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val barcode = data?.extras?.getString(Constants.BAR_CODE)
        if (barcode == "") {
            Toast.makeText(this@MainActivity, Constants.BAR_CODE_NOT_FOUND, Toast.LENGTH_LONG)
                .show()
        } else {
            val rootJsonObject = JSONObject(barcode)
            getViewModel().addScannedEntryToDB(rootJsonObject.getString("vmType"))
            Log.i("Barcode", rootJsonObject.getString("vmType"))
        }
    }

    private fun launchActivity(clss: Class<*>) {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            mClss = clss
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), ZXING_CAMERA_PERMISSION
            )
        } else {
            val intent = Intent(this, clss)
            startActivityForResult(intent, 2)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            ZXING_CAMERA_PERMISSION -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! do the
                    // calendar task you need to do.
                    Toast.makeText(this, Constants.PRESS_AGAIN_TO_SCAN, Toast.LENGTH_LONG).show()
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return
            }
        }// other 'switch' lines to check for other
        // permissions this app might request
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getBindingVariable(): Int {
        return BR.vm
    }

    override fun initializeViews(bundle: Bundle?) {
        getViewDataBinding()?.lifecycleOwner = this
        openScanner.setOnClickListener {
            launchActivity(ScannerViewActivity::class.java)
        }
        getViewDataBinding()?.launchApp?.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }
        //for button visibility
        getViewModel().checkForEmptyDB()
    }

    override fun getViewModel(): MainViewModel {
        val viewModelFactory = DependencyProvider.provideMainViewModelFactory(this)
        return ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

    }
}
