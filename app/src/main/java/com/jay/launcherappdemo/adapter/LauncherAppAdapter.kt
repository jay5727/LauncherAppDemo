package com.jay.launcherappdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.jay.launcherappsdk.model.LauncherApplicationMetaData
import com.jay.launcherappdemo.databinding.ItemLauncherAppBinding
import com.jay.launcherappdemo.listener.ItemClickListener
import com.jay.launcherappdemo.viewmodel.LauncherAppRowViewModel
import com.jay.launcherappdemo.viewmodel.LauncherAppViewModel
import java.util.*
import kotlin.collections.ArrayList

/**
 * @param context object to access resources
 * @param launcherApplicationList list containing all information of all the installed application
 * @param viewModel viewModel bound to [com.jay.launcherappdemo.view.LauncherAppActivity]
 * implements [Filterable] for searching
 */
class LauncherAppAdapter(
    private val context: Context,
    private val launcherApplicationList: ArrayList<LauncherApplicationMetaData>,
    private val viewModel: LauncherAppViewModel
) : RecyclerView.Adapter<LauncherAppAdapter.ViewHolder>(), Filterable {

    private var itemClickListener: ItemClickListener? = null

    var filterList = ArrayList<LauncherApplicationMetaData>()

    init {
        filterList = launcherApplicationList
        //make sure to assign initial list state to Observable
        viewModel.isEmptyList.set(filterList.isNullOrEmpty())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLauncherAppBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        binding.vm = LauncherAppRowViewModel()
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filterList.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterList[position])
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClicked(filterList[position].packageName)
        }

    }

    /**
     * Overridden method of [Filterable] interface
     */
    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                filterList = if (charSearch.isEmpty()) {
                    launcherApplicationList
                } else {
                    val resultList = ArrayList<LauncherApplicationMetaData>()
                    for (row in launcherApplicationList) {
                        if (row.appName?.toLowerCase(Locale.ROOT)
                                ?.contains(charSearch.toLowerCase(Locale.ROOT))!!
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = filterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterList = results?.values as ArrayList<LauncherApplicationMetaData>
                viewModel.isEmptyList.set(filterList.isNullOrEmpty())
                notifyDataSetChanged()
            }

        }
    }

    /**
     * @param binding : binding file for Item Row Layout [ItemLauncherAppBinding]
     */
    inner class ViewHolder(private val binding: ItemLauncherAppBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: LauncherApplicationMetaData) {
            binding.vm?.item?.set(item)
            binding.executePendingBindings()
        }
    }

    /**
     * Sets [ItemClickListener] for listening launcher application item click.
     * @param clickListener Listener object
     */
    internal fun setOnItemClickListener(clickListener: ItemClickListener?) {
        itemClickListener = clickListener
    }
}