package com.android.foodapp

import com.android.foodapp.model.EmployeeVacationBalancesResponse

interface OnClickRowListener {
    fun onClickItem(
        position: Int,
        item: EmployeeVacationBalancesResponse,
    )
}