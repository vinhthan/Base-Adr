package com.example.videoexoplayer.ui.allow.main.videoFile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.videoexoplayer.R
import com.example.videoexoplayer.databinding.ActivityVideoFileBinding
import com.example.videoexoplayer.ui.base.BaseActivity

class VideoFileActivity : BaseActivity<ActivityVideoFileBinding, VideoFileViewModel>() {

    private lateinit var mAdapter: VideoFileAdapter

    override fun layoutRes(): Int = R.layout.activity_video_file

    override fun viewModelClass(): Class<VideoFileViewModel> = VideoFileViewModel::class.java

    override fun handleViewState() {
    }

    override fun initView() {

    }


}