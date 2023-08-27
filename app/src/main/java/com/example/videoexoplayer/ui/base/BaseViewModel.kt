package com.example.videoexoplayer.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel(){
    val viewState = SingleLiveEvent<Int>()

}