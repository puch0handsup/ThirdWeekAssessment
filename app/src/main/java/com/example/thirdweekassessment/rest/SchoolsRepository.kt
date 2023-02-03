package com.example.thirdweekassessment.rest

import android.util.Log
import com.example.thirdweekassessment.utils.FailureResponse
import com.example.thirdweekassessment.utils.NullSchoolsResponse
import com.example.thirdweekassessment.utils.UIState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "SchoolsRepositoryImpl"

interface SchoolsRepository {
    fun getSchools(): Flow<UIState>
}

class SchoolsRepositoryImpl @Inject constructor(
    private val schoolsApi: SchoolsApi
) : SchoolsRepository {

    override fun getSchools(): Flow<UIState> = flow {
        emit(UIState.LOADING)
        try {
            val response = schoolsApi.getAllSchools()
            if (response.isSuccessful) {
                response.body()?.let {
                    val temp = UIState.SUCCESS(response = it)
                    emit(temp)
                } ?: throw NullSchoolsResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }
}