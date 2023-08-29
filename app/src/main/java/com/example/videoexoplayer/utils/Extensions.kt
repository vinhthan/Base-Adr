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