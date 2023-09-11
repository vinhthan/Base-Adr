package com.example.videoexoplayer.ui.allow.main.videoFile

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoexoplayer.R
import com.example.videoexoplayer.data.model.MediaFiles
import com.example.videoexoplayer.databinding.ActivityVideoFileBinding
import com.example.videoexoplayer.ui.base.BaseActivity

class VideoFileActivity : BaseActivity<ActivityVideoFileBinding, VideoFileViewModel>() {

    private lateinit var mAdapter: VideoFileAdapter
    private var folderName = ""

    override fun layoutRes(): Int = R.layout.activity_video_file

    override fun viewModelClass(): Class<VideoFileViewModel> = VideoFileViewModel::class.java

    override fun handleViewState() {
    }

    override fun initView() {
        folderName = intent.getStringExtra("folderName").toString()
        supportActionBar?.title = folderName
        showVideoFiles()
    }

    private fun showVideoFiles() {
        mAdapter = VideoFileAdapter()
        mAdapter.listVideo = fetchMedia(folderName)
        binding.rvVideo.apply {
            layoutManager = LinearLayoutManager(this@VideoFileActivity, LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
            adapter = mAdapter
        }
        mAdapter.notifyDataSetChanged()

    }

    @SuppressLint("Range")
    private fun fetchMedia(folderName: String): MutableList<MediaFiles> {
        val videoFiles = arrayListOf<MediaFiles>()
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val selection = "${MediaStore.Video.Media.DATA} like?"
        val selectionArg = arrayOf<String>("%$folderName%")
        val cursor = contentResolver.query(uri, null, selection, selectionArg, null)
        if (cursor != null && cursor.moveToNext()) {
            do {
                val id = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media._ID))
                val title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE))
                val displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DISPLAY_NAME))
                val size = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE))
                val duration = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION))
                val path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA))
                val dateAdded = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATE_ADDED))
                val mediaFiles = MediaFiles(id, title, displayName, size, duration, path, dateAdded)

                videoFiles.add(mediaFiles)
            }while (cursor.moveToNext())
        }
        return videoFiles
    }


}