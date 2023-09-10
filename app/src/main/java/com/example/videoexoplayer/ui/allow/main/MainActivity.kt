package com.example.videoexoplayer.ui.allow.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.videoexoplayer.R
import com.example.videoexoplayer.data.model.MediaFiles
import com.example.videoexoplayer.databinding.ActivityMainBinding
import com.example.videoexoplayer.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var mAdapterVideoFolders: VideoFoldersAdapter
    private var allFolderList = mutableListOf<String>()
    companion object {
        fun startActivity(activity: Activity, bundle: Bundle? = null) {
            val intent = Intent(activity, MainActivity::class.java)
            if (bundle != null) {
                intent.putExtras(bundle)
            }
            activity.startActivity(intent)
        }
    }

    override fun layoutRes(): Int = R.layout.activity_main

    override fun viewModelClass(): Class<MainViewModel> = MainViewModel::class.java

    override fun handleViewState() {

    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onResume() {
        super.onResume()
        if (checkSelfPermission(Manifest.permission.READ_MEDIA_VIDEO) == PackageManager.PERMISSION_DENIED) {
            val alertDialog = AlertDialog.Builder(this)
            alertDialog.setTitle("App Permission")
                .setMessage("For playing videos, you must allow this app to access video files on your device \n\n Open Setting from below button")
                .setPositiveButton("Open Setting") { dialog, _ ->
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                    dialog.dismiss()
                }
            alertDialog.show()
        }
    }

    override fun initView() {
        showFolders()

        binding.swipeRefreshFolder.setOnRefreshListener {
            showFolders()
            binding.swipeRefreshFolder.isRefreshing = false
        }

    }

    private fun showFolders() {
        mAdapterVideoFolders = VideoFoldersAdapter()
        mAdapterVideoFolders.listMediaFile = fetchMedia()
        binding.rvFolder.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            hasFixedSize()
            adapter = mAdapterVideoFolders
        }
        mAdapterVideoFolders.notifyDataSetChanged()

    }

    @SuppressLint("Range")
    private fun fetchMedia(): MutableList<MediaFiles> {
        val mediaFilesArrayList = ArrayList<MediaFiles>()
        val uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null, null)
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

                val index = path.lastIndexOf("/")
                val subString = path.subSequence(0, index -1)
                if (!mAdapterVideoFolders.folderPath.contains(subString)) {
                    if (index < path.length) {
                        mAdapterVideoFolders.folderPath.add(subString as String)
                        mediaFilesArrayList.add(mediaFiles)
                    }
                }
            } while (cursor.moveToNext())
        }
        return mediaFilesArrayList
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_folder, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_search -> Toast.makeText(this, "search", Toast.LENGTH_SHORT).show()
            R.id.menu_rate -> {
                val uri = Uri.parse("https://play.google.com/store/apps/details?id=${applicationContext.packageName}")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            R.id.menu_share -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=${applicationContext.packageName}")
                intent.type = "text/plain"
                startActivity(Intent.createChooser(intent, "Share app via"))
            }
        }

        return super.onOptionsItemSelected(item)
    }




}