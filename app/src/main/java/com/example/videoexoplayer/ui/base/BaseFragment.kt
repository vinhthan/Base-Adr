package com.example.videoexoplayer.ui.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<T: ViewDataBinding, M: BaseViewModel>: Fragment(){
    protected lateinit var binding: T
    protected lateinit var viewModel: M

    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @LayoutRes
    protected abstract fun layoutRes(): Int

    protected abstract fun viewModelClass(): Class<M>

    protected abstract fun initView()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes(), container, false)
        binding.lifecycleOwner = this
        viewModelFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(requireContext() as Application)
        viewModel = ViewModelProvider(this, viewModelFactory)[viewModelClass()]


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun setChangeColorStatusBar(color: Int, activity: Activity) {
        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, color)
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }




}