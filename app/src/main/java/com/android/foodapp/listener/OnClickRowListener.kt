package com.android.foodapp.listener

import com.android.foodapp.model.ApiResponse

interface OnClickRowListener {
    fun onClickItem(
        position: Int,
        item: ApiResponse,
    )
}