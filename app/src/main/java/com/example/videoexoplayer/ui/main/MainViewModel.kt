package com.example.videoexoplayer.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.videoexoplayer.ui.base.BaseViewModel

class MainViewModel: BaseViewModel() {
    private var _contentText = MutableLiveData<String>("bla bla...")
    val contentText: LiveData<String> get() = _contentText

    fun setContentText() {
        _contentText.value = "VinhTM xin chao"
    }
}