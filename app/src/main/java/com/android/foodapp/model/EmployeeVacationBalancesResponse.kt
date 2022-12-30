package com.android.foodapp.model

data class EmployeeVacationBalancesResponse(
    val vacation_Type: String,
    val vacation_balance: Double,
    val year: String,
    val branch_code: String,
    val company_code: String,
    val employee_code: String,
    val employee_name_a: String,
    val new_year_balance: Double,
    val vacation_previous_balance: Double,
    val vacation_up_to_end_of_year_balance: Double
)