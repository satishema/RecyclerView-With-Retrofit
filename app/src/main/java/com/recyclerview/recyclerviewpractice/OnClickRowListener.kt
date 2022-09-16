package com.recyclerview.recyclerviewpractice

import com.recyclerview.recyclerviewpractice.model.ApiResponse

interface OnClickRowListener {
    fun onClickItem(
        position: Int,
        item: ApiResponse,
    )
}