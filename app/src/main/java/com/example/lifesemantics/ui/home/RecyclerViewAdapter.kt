package com.example.lifesemantics.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesemantics.data.entity.Item
import com.example.lifesemantics.databinding.ItemBinding
import com.example.lifesemantics.listener.ItemClickListener

class RecyclerViewAdapter(private val listener: ItemClickListener) : ListAdapter<Item, RecyclerViewAdapter.MyViewHolder>(
    diffUtil
) {

    class MyViewHolder(private val binding: ItemBinding, private val listener: ItemClickListener) :  RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: Item) {
            binding.data = item

            itemView.setOnClickListener {
                listener.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: ItemBinding = ItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding, listener)
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