package com.example.lifesemantics.listener

import com.example.lifesemantics.data.entity.Item

interface ItemClickListener {
    fun onClick(item: Item)
}