package com.example.thirdweekassessment.utils

import com.example.thirdweekassessment.model.SchoolsItem

sealed class UIState {
    object LOADING : UIState()
    data class SUCCESS(val response: List<SchoolsItem>) : UIState()
    data class ERROR(val error: Exception) : UIState()
}
