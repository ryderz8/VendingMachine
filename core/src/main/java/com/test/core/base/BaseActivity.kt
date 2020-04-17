package com.test.core.base

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.test.core.HandleOnceLiveEvent

/**
 * Base activity for the application to have a streamlined functionality across Activities
 */
abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    private var mViewDataBinding: B? = null
    private var mViewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        observeForAlert(mViewModel);
        performDataBinding()
        initializeViews(savedInstanceState)
    }

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun getBindingVariable(): Int

    protected abstract fun initializeViews(bundle: Bundle?)

    abstract fun getViewModel(): VM

    fun getViewDataBinding(): B? {
        return mViewDataBinding
    }

    /**
     *
     */
    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()
        //provide lifecycle owner for binding directly to the view from viewModel
        mViewDataBinding?.lifecycleOwner = this
    }

    /**
     *
     * @param viewModel
     */
    private fun observeForAlert(viewModel: BaseViewModel?) {
        if (viewModel == null) return
        viewModel.alertObservable().observe(this, alertObserver)
    }

    private var alertObserver: Observer<HandleOnceLiveEvent<String>> = Observer {
        //check if content already handled, if its handled then no need to consider
        if (!TextUtils.isEmpty(it.contentIfNotHandled)) {
            //call alert
            showToast(it.peekContent())
        }
    }

    /**
     *
     */
    private fun showToast(msg: String) {
        //TODO:
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel?.alertObservable()?.removeObserver(alertObserver)
    }
}