package com.main.app30daysrmv.model

import androidx.annotation.DrawableRes

data class Day(
    val id: Int,
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)