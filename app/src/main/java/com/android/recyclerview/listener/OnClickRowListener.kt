package com.android.recyclerview.listener

import com.android.recyclerview.model.ApiResponse

interface OnClickRowListener {
    fun onClickItem(
        position: Int,
        item: ApiResponse,
    )
}