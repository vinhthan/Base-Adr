package com.example.videoexoplayer.ui.allow.main.videoFile.videoPlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.videoexoplayer.R
import com.example.videoexoplayer.databinding.ActivityVideoPlayerBinding
import com.example.videoexoplayer.ui.base.BaseActivity

class VideoPlayerActivity : BaseActivity<ActivityVideoPlayerBinding, VideoPlayerViewModel>() {
    override fun layoutRes(): Int = R.layout.activity_video_player

    override fun viewModelClass(): Class<VideoPlayerViewModel> = VideoPlayerViewModel::class.java

    override fun handleViewState() {
    }

    override fun initView() {

    }


}