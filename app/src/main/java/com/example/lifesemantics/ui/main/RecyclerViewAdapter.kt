package com.example.lifesemantics.ui.main

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesemantics.data.entity.Item
import com.example.lifesemantics.databinding.ItemBinding
import com.example.lifesemantics.ui.detail.DetailActivity

class RecyclerViewAdapter() : ListAdapter<Item, RecyclerViewAdapter.MyViewHolder>(diffUtil) {

    class MyViewHolder(private val binding: ItemBinding) :  RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: Item) {
            binding.data = item

            itemView.setOnClickListener {
                Intent(root.context, DetailActivity::class.java).apply {
                    putExtra("data", item)
                }.run { root.context.startActivity(this) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {

        val diffUtil = object : DiffUtil.ItemCallback<Item>() {

            override fun areItemsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean {
                return (oldItem.hospUrl == newItem.hospUrl)
            }

            override fun areContentsTheSame(
                oldItem: Item,
                newItem: Item
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}