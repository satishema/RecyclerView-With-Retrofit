package com.android.foodapp

import com.android.foodapp.model.ApiResponse

interface OnClickRowListener {
    fun onClickItem(
        position: Int,
        item: ApiResponse,
    )
}