package com.jay.launcherappdemo.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.jay.launcherappsdk.model.LauncherApplicationMetaData

/** 
 * View model for row item of Launcher Application List inherits [ViewModel] 
 */
class LauncherAppRowViewModel : ViewModel() {
    val item = ObservableField<LauncherApplicationMetaData>()
}