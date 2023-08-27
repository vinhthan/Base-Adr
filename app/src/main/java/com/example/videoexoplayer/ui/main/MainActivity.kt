package com.example.videoexoplayer.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.videoexoplayer.R
import com.example.videoexoplayer.databinding.ActivityMainBinding
import com.example.videoexoplayer.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): MainViewModel = MainViewModel::class.java

    override fun handleViewState() {}

    override fun initView() {
        binding.textview.text = "VinhTM"
    }


}