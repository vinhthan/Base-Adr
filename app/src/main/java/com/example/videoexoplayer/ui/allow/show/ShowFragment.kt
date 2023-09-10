package com.example.videoexoplayer.ui.allow.show

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