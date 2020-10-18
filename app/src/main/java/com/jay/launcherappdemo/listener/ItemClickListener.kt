package com.jay.launcherappdemo.listener

/**
 * An interface to track the clicks on launcher applications
 */
interface ItemClickListener {

    /**
     * Callback for Item clicked
     *
     * @param packageName clicked launcher application's package name
     */
    fun onItemClicked(packageName: String?)
}