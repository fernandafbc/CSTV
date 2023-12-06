package com.fernanda.cstv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var showSplashScreen = true

    init {
        viewModelScope.launch {
            delay(2_000)
            showSplashScreen = false
        }
    }

}