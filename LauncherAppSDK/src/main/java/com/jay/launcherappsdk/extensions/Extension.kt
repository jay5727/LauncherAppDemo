package com.jay.launcherappsdk.extensions

import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.ResolveInfo
import com.jay.launcherappsdk.model.LauncherApplicationMetaData

/**
 * Extension method to query package manager,applied on Context object from Activity/Fragment
 * @return sorted List[LauncherApplicationMetaData] containing information such as
 * appName, packageName, icon,
 * launcherActivityName,
 * versionCode, versionName
 */
fun ContextWrapper.getLauncherApplicationList(): List<LauncherApplicationMetaData> {
    val returnValue = mutableListOf<LauncherApplicationMetaData>()

    val mainIntent = Intent(Intent.ACTION_MAIN, null)
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)

    val list: List<ResolveInfo> = packageManager.queryIntentActivities(mainIntent, 0)
    for (i in list.indices) {
        val listInfo = list[i]
        // 1 Get the application name
        val appName = listInfo.loadLabel(packageManager).toString()
        // 2 Get the packageName name
        val packageName = listInfo.activityInfo.packageName
        // 3 Get the icon
        val icon = listInfo.loadIcon(packageManager)
        // 4 Get the Launcher Activity Name
        val launcherActivityName = listInfo.activityInfo.name
        //Get the package Info
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        // 5 Get the version code
        val versionCode = packageInfo.versionCode
        // 6 Get the version code
        val versionName = packageInfo.versionName

        returnValue.add(
            LauncherApplicationMetaData(
                appName = appName,
                packageName = packageName,
                icon = icon,
                launcherActivityName = launcherActivityName,
                versionCode = versionCode,
                versionName = versionName
            )
        )
    }
    //make sure to sort before returning the list...
    return returnValue.sortedWith(compareByAppName)
}

/**
 * Comparator for sorting in Ascending Order based on App Name
 */
private val compareByAppName = Comparator<LauncherApplicationMetaData> { a1, a2 ->
    return@Comparator (a1.appName ?: "")?.compareTo(a2.appName ?: "")
}
