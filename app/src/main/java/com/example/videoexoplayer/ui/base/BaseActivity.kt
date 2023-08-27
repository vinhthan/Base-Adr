package com.example.videoexoplayer.ui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<T: ViewDataBinding, M: BaseViewModel>: AppCompatActivity() {

    protected lateinit var binding: T
    protected lateinit var viewModel: BaseViewModel

    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewModelClass(): Class<M>

    protected abstract fun handleViewState()

    protected abstract fun initView()

    var saveInstanceState: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.saveInstanceState = saveInstanceState
        binding = DataBindingUtil.setContentView(this, layoutRes())
        binding.lifecycleOwner = this
        //val viewModelProvider = ViewModelProvider(this, viewModelFactory)
        //viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]
        //viewModel = BaseViewModel

        initView()
        //viewModel.viewState




    }



}