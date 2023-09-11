package com.example.videoexoplayer.ui.allow.main.videoFile

import android.content.Intent
import android.text.format.Formatter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videoexoplayer.R
import com.example.videoexoplayer.data.model.MediaFiles
import com.example.videoexoplayer.ui.allow.main.videoFile.videoPlayer.VideoPlayerActivity
import com.example.videoexoplayer.utils.timeConversion

class VideoFileAdapter: RecyclerView.Adapter<VideoFileAdapter.VideoFileViewHolder>() {
    var listVideo = mutableListOf<MediaFiles>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoFileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoFileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listVideo.size
    }

    override fun onBindViewHolder(holder: VideoFileViewHolder, position: Int) {
        holder.bind(listVideo[position])
    }

    class VideoFileViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imgThumbnail = itemView.findViewById<ImageView>(R.id.img_video)
        private val imgMoreVideo = itemView.findViewById<ImageView>(R.id.img_more_video)
        private val tvNameVideo = itemView.findViewById<TextView>(R.id.tv_name_video)
        private val tvSizeVideo = itemView.findViewById<TextView>(R.id.tv_size_video)
        private val tvDurationVideo = itemView.findViewById<TextView>(R.id.tv_duration_video)
        fun bind(mediaFiles: MediaFiles) {
            tvNameVideo.text = mediaFiles.displayName
            tvSizeVideo.text = Formatter.formatFileSize(itemView.context, mediaFiles.size!!.toLong())
            val milliSeconds: Double = mediaFiles.duration!!.toDouble()
            tvDurationVideo.text = timeConversion(milliSeconds.toLong())
            Glide.with(itemView.context).load(mediaFiles.path).into(imgThumbnail)


            itemView.setOnClickListener {
                val intent = Intent(itemView.context, VideoPlayerActivity::class.java)

                itemView.context.startActivity(intent)
            }

            imgMoreVideo.setOnClickListener {
                Toast.makeText(itemView.context, "more", Toast.LENGTH_SHORT).show()
            }


        }

    }





}