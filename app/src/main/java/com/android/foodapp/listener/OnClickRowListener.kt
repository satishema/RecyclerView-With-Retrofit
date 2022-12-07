package com.android.foodapp.listener

import com.android.foodapp.model.FoodResponse

interface OnClickRowListener {
    fun onClickItem(
        position: Int,
        item: FoodResponse,
    )
}