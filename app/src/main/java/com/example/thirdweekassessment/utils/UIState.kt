package com.example.thirdweekassessment.utils

import com.example.thirdweekassessment.model.SchoolsItem

sealed class UIState<out T> {
    object LOADING : UIState<Nothing>()
    data class SUCCESS<T>(val response: T) : UIState<T>()
    data class ERROR(val error: Exception) : UIState<Nothing>()
}
