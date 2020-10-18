package com.jay.launcherappdemo.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_PACKAGE_ADDED
import android.content.Intent.ACTION_PACKAGE_REMOVED
import android.util.Log
import android.widget.Toast
import com.jay.launcherappdemo.R


/**
 * Custom Broadcast to listen App Added/Removed changes inherits [BroadcastReceiver]
 */
class LauncherAppNotificationReceiver : BroadcastReceiver() {

    val TAG = LauncherAppNotificationReceiver::class.java.simpleName

    override fun onReceive(context: Context?, intent: Intent?) {
        when {
            intent?.action?.equals(ACTION_PACKAGE_ADDED) == true -> {
                Toast.makeText(context, context?.getString(R.string.installed), Toast.LENGTH_SHORT)
                    .show()
            }
            intent?.action?.equals(ACTION_PACKAGE_REMOVED) == true -> {
                Toast.makeText(context, context?.getString(R.string.uninstalled), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Log.i(TAG, "Unknown Type" + intent?.action)

            }
        }
    }
}
