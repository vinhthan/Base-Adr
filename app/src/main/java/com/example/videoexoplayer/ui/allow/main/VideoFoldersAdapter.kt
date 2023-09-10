package com.example.videoexoplayer.ui.allow.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.videoexoplayer.R
import com.example.videoexoplayer.data.model.MediaFiles
import com.example.videoexoplayer.ui.allow.main.videoFile.VideoFileActivity

class VideoFoldersAdapter: RecyclerView.Adapter<VideoFoldersAdapter.VideoFoldersViewHolder>() {

    var listMediaFile = mutableListOf<MediaFiles>()
    var folderPath = mutableListOf<String>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoFoldersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_video_folders, parent, false)
        return VideoFoldersViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listMediaFile.size
    }

    override fun onBindViewHolder(holder: VideoFoldersViewHolder, position: Int) {
        holder.bind(listMediaFile[position], folderPath)

        /*val indexPath = folderPath[position].lastIndexOf("/")
        if (indexPath != null) {
            val nameFolder = folderPath[position].substring(indexPath + 1)
            holder.tvNameFolder.text = nameFolder
            holder.tvPathFolder.text = folderPath[position]
            holder.tvNumberVideo.text = "5 Videos"

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.imgFolder.context, VideoFileActivity::class.java)
                intent.putExtra("folderName", nameFolder)
                holder.itemView.context.startActivity(intent)
            }
        }*/

    }

    class VideoFoldersViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         val imgFolder = itemView.findViewById<ImageView>(R.id.img_folder)
        val tvNameFolder = itemView.findViewById<TextView>(R.id.tv_name_folder)
         val tvPathFolder = itemView.findViewById<TextView>(R.id.tv_path_foder)
         val tvNumberVideo = itemView.findViewById<TextView>(R.id.tv_number_video_in_folder)
        fun bind(mediaFile: MediaFiles, folderPath: MutableList<String>) {
            val indexPath = folderPath[adapterPosition].lastIndexOf("/" )
            val nameFolder = folderPath[adapterPosition].substring(indexPath + 1)
            tvNameFolder.text = nameFolder
            tvPathFolder.text = folderPath[adapterPosition]
            tvNumberVideo.text = "5 Videos"

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, VideoFileActivity::class.java)
                intent.putExtra("folderName", nameFolder)
                itemView.context.startActivity(intent)
            }






        }
    }





}