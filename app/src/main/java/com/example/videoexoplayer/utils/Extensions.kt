package com.example.videoexoplayer.utils

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Locale

fun Double.decimalOneCommas(): String {
    val symbols = DecimalFormatSymbols(Locale.US)
    symbols.decimalSeparator = '.'
    symbols.groupingSeparator = ','
    val decimalFormat = DecimalFormat("#,###.##")
    decimalFormat.maximumFractionDigits = 1
    decimalFormat.decimalFormatSymbols = symbols
    decimalFormat.isGroupingUsed = true
    return decimalFormat.format(this)
}

@SuppressLint("SimpleDateFormat")
fun setSimpleDateFormat(time: Long): String {
    val simpleDate = SimpleDateFormat("HH:mm, MMM dd, yyyy")
    return simpleDate.format(time)
}

fun timeConversion(value: Long): String {
    var videoTime = ""
    val duration = value.toInt()
    val hours = (duration/3600000)
    val minutes = (duration/60000) % 60000
    val seconds = duration % 60000/1000
    if (hours > 0) {
        videoTime = String.format("%02d:%02d:%02d", hours, minutes, seconds)

    } else {
        videoTime = String.format("%02d:%02d", minutes, seconds)
    }
    return videoTime

}