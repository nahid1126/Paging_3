package com.nahid.paging3kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nahid.paging3kotlin.databinding.ItemLoadBinding

class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoadHolder>() {

    override fun onBindViewHolder(holder: LoadHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadHolder {
        val view = ItemLoadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadHolder(view)
    }

    class LoadHolder(private val binding: ItemLoadBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}