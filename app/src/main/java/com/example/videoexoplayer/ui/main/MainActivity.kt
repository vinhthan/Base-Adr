package com.example.videoexoplayer.ui.main

import com.example.videoexoplayer.R
import com.example.videoexoplayer.databinding.ActivityMainBinding
import com.example.videoexoplayer.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun handleViewState() {}

    override fun initView() {
        binding.viewModel = viewModel
        //content

        viewModel.setContentText()
    }


}