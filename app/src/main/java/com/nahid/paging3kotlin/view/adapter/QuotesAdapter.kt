package com.nahid.paging3kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nahid.paging3kotlin.databinding.RowItemBinding
import com.nahid.paging3kotlin.model.Result

class QuotesAdapter : PagingDataAdapter<Result, QuotesAdapter.QuotesViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        holder.binding(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view = RowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuotesViewHolder(view)
    }

    class QuotesViewHolder(private val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun binding(item: Result?) {
                binding.result = item
            }
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem._id == newItem._id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }

        }
    }
}