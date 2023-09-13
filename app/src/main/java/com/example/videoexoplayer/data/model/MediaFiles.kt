package com.example.videoexoplayer.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MediaFiles (
    val id: String? = null,
    val title: String? = null,
    val displayName: String? = null,
    val size: String? = null,
    val duration: String? = null,
    val path: String? = null,
    val dateAdded: String? = null,
) : Parcelable