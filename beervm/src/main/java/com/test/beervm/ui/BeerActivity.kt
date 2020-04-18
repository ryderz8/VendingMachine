package com.test.beervm.ui

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.textfield.TextInputEditText
import com.test.beervm.BR
import com.test.beervm.R
import com.test.beervm.data.DependencyProvider
import com.test.beervm.databinding.ActivityBeerBinding
import com.test.beervm.viewmodels.BeerViewModel
import com.test.core.base.BaseActivity

class BeerActivity : BaseActivity<ActivityBeerBinding, BeerViewModel>() {

    private lateinit var beerListAdapter: BeerListAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_beer
    }

    override fun getBindingVariable(): Int {
        return BR.beerviewmodel
    }

    override fun initializeViews(bundle: Bundle?) {
        getViewDataBinding()?.lifecycleOwner = this
        initRecyclerView()
        getViewDataBinding()?.fab?.setOnClickListener {
            showDialog()
        }
    }

    private fun initRecyclerView() {
        beerListAdapter = BeerListAdapter()
        getViewDataBinding()?.recyclerview?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getViewDataBinding()?.recyclerview?.adapter = beerListAdapter
    }

    override fun getViewModel(): BeerViewModel {
        val dependency = DependencyProvider.provideBeerViewModelFactory(this)
        return ViewModelProvider(this, dependency).get(BeerViewModel::class.java)
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.custom_dialog_beer)

        val submitBtn = dialog.findViewById(R.id.submit) as Button
        val priceET = dialog.findViewById(R.id.price_et) as TextInputEditText
        val quantET = dialog.findViewById(R.id.quant_et) as TextInputEditText
        val typeSpinner = dialog.findViewById(R.id.type_spinner) as Spinner

        submitBtn.setOnClickListener {
            if (!TextUtils.isEmpty(priceET.text) && !TextUtils.isEmpty(quantET.text)) {
                getViewModel().updateItem(
                    price = priceET.text.toString(), quant = quantET.text.toString(),
                    type = typeSpinner.selectedItem.toString()
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