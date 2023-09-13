package com.example.videoexoplayer.ui.allow.main.videoFile.videoPlayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.media3.common.C
import androidx.media3.common.Player
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.SimpleExoPlayer
import androidx.media3.exoplayer.source.ConcatenatingMediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.example.videoexoplayer.R
import com.example.videoexoplayer.data.model.MediaFiles
import com.example.videoexoplayer.databinding.ActivityVideoPlayerBinding
import com.example.videoexoplayer.ui.base.BaseActivity

class VideoPlayerActivity : BaseActivity<ActivityVideoPlayerBinding, VideoPlayerViewModel>() {

    var mVideoFiles = mutableListOf<MediaFiles>()
    var position = 1
    var videoTitle = ""
    lateinit var player: SimpleExoPlayer
    lateinit var tvTitle: TextView
    lateinit var concatenatingMediaSource: ConcatenatingMediaSource


    override fun layoutRes(): Int = R.layout.activity_video_player

    override fun viewModelClass(): Class<VideoPlayerViewModel> = VideoPlayerViewModel::class.java

    override fun handleViewState() {
    }

    override fun initView() {
        binding.viewModel = viewModel
        position = intent.getIntExtra("position", 1)
        videoTitle = intent.getStringExtra("video_title").toString()
        mVideoFiles = intent.extras!!.getParcelableArrayList("videoArrayList")!!
        tvTitle = findViewById<TextView>(R.id.tv_title)
        tvTitle.text = videoTitle

        playVideo()



    }

    private fun playVideo() {
        var path = mVideoFiles[position].path
        var uri = Uri.parse(path)
        player = SimpleExoPlayer.Builder(this).build()
        val dataSourceFactory = DefaultDataSourceFactory(this,
        Util.getUserAgent(this, "app"))
        concatenatingMediaSource = ConcatenatingMediaSource()
        for (i in 0 until mVideoFiles.size) {
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(uri.toString()))
            concatenatingMediaSource.addMediaSource(mediaSource)
        }
        binding.playerView.player = player
        binding.playerView.keepScreenOn = true
        player.prepare(concatenatingMediaSource)
        player.seekTo(position, C.TIME_UNSET)
        playError()



    }

    private fun playError() {
        player.addListener()
        player.playWhenReady = true
    }


}