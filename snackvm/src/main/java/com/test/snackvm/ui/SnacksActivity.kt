package com.test.snackvm.ui

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.test.core.base.BaseActivity
import com.test.snackvm.BR
import com.test.snackvm.R
import com.test.snackvm.data.DependencyProvider
import com.test.snackvm.databinding.ActivitySnacksBinding
import com.test.snackvm.viewmodel.SnacksViewModel

class SnacksActivity : BaseActivity<ActivitySnacksBinding, SnacksViewModel>() {

    private lateinit var snackListAdapter: SnackListAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_snacks
    }

    override fun getBindingVariable(): Int {
        return BR.snackviewmodel
    }

    override fun initializeViews(bundle: Bundle?) {
        getViewDataBinding()?.lifecycleOwner = this
        initRecyclerView()

        getViewDataBinding()?.fab?.setOnClickListener {
            showDialog()
        }
    }

    private fun initRecyclerView() {
        snackListAdapter = SnackListAdapter()
        getViewDataBinding()?.recyclerview?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getViewDataBinding()?.recyclerview?.adapter = snackListAdapter

    }

    override fun getViewModel(): SnacksViewModel {
        val viewModelFactory = DependencyProvider.provideSnacksViewModelFactory(this)
        return ViewModelProvider(this, viewModelFactory).get(SnacksViewModel::class.java)

    }


    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog)

        val submitBtn = dialog.findViewById(R.id.submit) as Button
        val nameET = dialog.findViewById(R.id.name_et) as TextInputEditText
        val priceET = dialog.findViewById(R.id.price_et) as TextInputEditText
        val quantET = dialog.findViewById(R.id.quant_et) as TextInputEditText
        val typeSpinner = dialog.findViewById(R.id.type_spinner) as Spinner

        submitBtn.setOnClickListener {
            if (!TextUtils.isEmpty(nameET.text) && !TextUtils.isEmpty(priceET.text) && !TextUtils.isEmpty(
                    quantET.text
                )
            ) {
                getViewModel().updateItem(
                    nameET.text.toString(), priceET.text.toString(), quantET.text.toString(),
                    typeSpinner.selectedItem.toString()
                )

                dialog.dismiss()
            } else {
                Toast.makeText(this, "Please fill the required fields!", Toast.LENGTH_SHORT).show()

            }
        }

        dialog.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

        dialog.show()

    }


}