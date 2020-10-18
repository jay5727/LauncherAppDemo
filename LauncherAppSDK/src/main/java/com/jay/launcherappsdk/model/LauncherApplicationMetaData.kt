package com.jay.launcherappsdk.model

import android.graphics.drawable.Drawable

/**
 * Model class containing information of Installed Applications
 *
 * @param appName Indicates the app name of Application
 * @param packageName: Indicates the application name of Application
 * @param icon: Indicates the icon[Drawable] of Application
 * @param launcherActivityName: Indicates the launcher Activity name of Application
 * @param versionCode: Indicates the version code of Application
 * @param versionName: Indicates the version name of Application
 */
data class LauncherApplicationMetaData(
    var appName: String? = null,
    var packageName: String? = null,
    var icon: Drawable? = null,
    var launcherActivityName: String? = null,
    var versionCode: Int? = null,
    var versionName: String? = null
)