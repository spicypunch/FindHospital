package com.example.lifesemantics.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lifesemantics.data.entity.BasicHospitalInfoEntity
import com.example.lifesemantics.databinding.ItemBinding

class RecyclerViewAdapter() : ListAdapter<BasicHospitalInfoEntity, RecyclerViewAdapter.MyViewHolder>(diffUtil) {

    class MyViewHolder(private val binding: ItemBinding) :  RecyclerView.ViewHolder(binding.root) {
        val root = binding.root

        fun bind(item: BasicHospitalInfoEntity) {

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

        val diffUtil = object : DiffUtil.ItemCallback<BasicHospitalInfoEntity>() {

            override fun areItemsTheSame(
                oldItem: BasicHospitalInfoEntity,
                newItem: BasicHospitalInfoEntity
            ): Boolean {
                return (oldItem.hospitalUrl == newItem.hospitalUrl)
            }

            override fun areContentsTheSame(
                oldItem: BasicHospitalInfoEntity,
                newItem: BasicHospitalInfoEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }
}