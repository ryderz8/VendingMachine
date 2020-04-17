package com.test.core.base

import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.test.core.HandleOnceLiveEvent
import com.test.core.R
import com.test.core.ToolbarNavInterface


/**
 *
 */
abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel> : Fragment(),
    ToolbarNavInterface {

    // Root view for this fragment
    private var mFragmentView: View? = null
    private var mViewDataBinding: B? = null
    private var mViewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = getViewModel()
        observeForAlert(mViewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater, initializeLayoutId(), container, false)
        mFragmentView = mViewDataBinding?.root
        return mFragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding?.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding?.executePendingBindings()

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initializeViews(savedInstanceState)
        initializeController()
    }

    /**
     * Initialize fragment controller.
     */
    protected abstract fun initializeController()

    /**
     * @return returns layout id of the fragment.
     */
    protected abstract fun initializeLayoutId(): Int

    /**
     * Initialize fragment views.
     */
    protected abstract fun initializeViews(savedInstanceState: Bundle?)

    protected abstract fun getViewModel(): VM

    protected abstract fun getBindingVariable(): Int

    fun getViewDataBinding(): B? {
        return mViewDataBinding
    }

    /**
     * Observer for common alerts across the app
     * @param viewModel
     */
    private fun observeForAlert(viewModel: BaseViewModel?) {
        if (viewModel == null) return
        viewModel.alertObservable().observe(this, alertObserver)
    }

    var alertObserver: Observer<HandleOnceLiveEvent<String>> = Observer {
        //check if content already handled, if its handled then no need to consider
        if (!TextUtils.isEmpty(it.contentIfNotHandled)) {
            //call alert
            showDialog(it.peekContent())
        }
    }

    /**
     *
     */
    fun showDialog(msg: String) {
        this.context?.let { context ->
            val builder =
                AlertDialog.Builder(context)
            builder.setTitle(context.getString(R.string.app_name))
            builder.setMessage(msg)
            builder.setCancelable(false)
            builder.setPositiveButton(
                context.getString(R.string.dialog_ok)
            ) { dialog: DialogInterface, which: Int ->
                dialog.dismiss()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel?.alertObservable()?.removeObserver(alertObserver)
    }

    override fun displayHomeAsUpEnabled(isEnable: Boolean) {
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(isEnable)
    }


}