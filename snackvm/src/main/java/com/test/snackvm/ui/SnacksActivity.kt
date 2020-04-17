package com.test.snackvm.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
        getViewModel().getListItem()
    }

    private fun initRecyclerView(){
        snackListAdapter = SnackListAdapter()
        getViewDataBinding()?.recyclerview?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        getViewDataBinding()?.recyclerview?.adapter = snackListAdapter

    }

    override fun getViewModel(): SnacksViewModel {
        val viewModelFactory = DependencyProvider.provideSnacksViewModelFactory(this)
        return ViewModelProvider(this, viewModelFactory).get(SnacksViewModel::class.java)

    }


}