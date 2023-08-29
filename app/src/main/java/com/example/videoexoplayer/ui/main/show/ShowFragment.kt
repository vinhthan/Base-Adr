package com.example.videoexoplayer.ui.main.show

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.videoexoplayer.R
import com.example.videoexoplayer.databinding.FragmentShowBinding
import com.example.videoexoplayer.ui.base.BaseFragment


class ShowFragment : BaseFragment<FragmentShowBinding, ShowViewModel>() {
    override fun layoutRes(): Int = R.layout.fragment_show

    override fun viewModelClass(): Class<ShowViewModel> = ShowViewModel::class.java

    override fun initView() {
        binding.viewModel = viewModel
        //content
    }




}