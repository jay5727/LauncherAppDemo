package com.jay.launcherappdemo.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

/** 
 *  View model for Launcher Application List inherits [ViewModel] 
 */
class LauncherAppViewModel : ViewModel() {
    val isEmptyList = ObservableBoolean(true)
}