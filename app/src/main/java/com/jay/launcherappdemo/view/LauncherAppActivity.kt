package com.jay.launcherappdemo.view

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import com.jay.launcherappsdk.extensions.getLauncherApplicationList
import com.jay.launcherappsdk.model.LauncherApplicationMetaData
import com.jay.launcherappdemo.R
import com.jay.launcherappdemo.adapter.LauncherAppAdapter
import com.jay.launcherappdemo.base.DataBindingActivity
import com.jay.launcherappdemo.databinding.ActivityLauncherAppBinding
import com.jay.launcherappdemo.listener.ItemClickListener
import com.jay.launcherappdemo.viewmodel.LauncherAppViewModel


/**
 * Launcher Activity for Application
 */
class LauncherAppActivity : DataBindingActivity(), ItemClickListener {

    private val binding: ActivityLauncherAppBinding by binding(R.layout.activity_launcher_app)
    private lateinit var adapter: LauncherAppAdapter
    private val viewModel by viewModels<LauncherAppViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@LauncherAppActivity
            vm = viewModel
        }
        setUpSearchView()
        setUpRecyclerView()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filter.filter(newText)
                return false
            }
        })
    }

    override fun onItemClicked(packageName: String?) {
        packageName?.let { name ->
            val launchIntent = packageManager.getLaunchIntentForPackage(name)
            launchIntent?.let { startActivity(it) }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.setOnItemClickListener(null)
    }

    /**
     * Method to set up SearchView
     */
    private fun setUpSearchView() {
        val searchIcon = binding.searchView.findViewById<ImageView>(R.id.search_mag_icon)
        searchIcon.setColorFilter(Color.BLACK)

        val cancelIcon = binding.searchView.findViewById<ImageView>(R.id.search_close_btn)
        cancelIcon.setColorFilter(Color.BLACK)

        val textView = binding.searchView.findViewById<TextView>(R.id.search_src_text)
        textView.setTextColor(Color.BLACK)
    }

    /**
     * Method to set up recycler view with adapter
     */
    private fun setUpRecyclerView() {
        adapter = LauncherAppAdapter(
            context = this,
            launcherApplicationList = getLauncherApplicationList().toMutableList() as ArrayList<LauncherApplicationMetaData>,
            viewModel = viewModel
        )
        binding.adapter = adapter
        adapter?.setOnItemClickListener(this)
    }
}