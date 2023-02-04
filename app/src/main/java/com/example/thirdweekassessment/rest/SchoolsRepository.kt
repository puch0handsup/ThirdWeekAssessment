package com.example.thirdweekassessment.rest

import android.util.Log
import com.example.thirdweekassessment.model.SatScoresItem
import com.example.thirdweekassessment.model.SchoolsItem
import com.example.thirdweekassessment.utils.FailureResponse
import com.example.thirdweekassessment.utils.NullSchoolsResponse
import com.example.thirdweekassessment.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

private const val TAG = "SchoolsRepositoryImpl"

interface SchoolsRepository {
    fun getSchools(): Flow<UIState<List<SchoolsItem>>>
    fun getSat(dbn: String): Flow<UIState<List<SatScoresItem>>>
}

class SchoolsRepositoryImpl @Inject constructor(
    private val schoolsApi: SchoolsApi
) : SchoolsRepository {

    override fun getSchools(): Flow<UIState<List<SchoolsItem>>> = flow {
        emit(UIState.LOADING)
        try {
            val response = schoolsApi.getAllSchools()
            if (response.isSuccessful) {
                response.body()?.let {
                    val temp = UIState.SUCCESS(it)
                    emit(temp)
                } ?: throw NullSchoolsResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

    override fun getSat(dbn: String): Flow<UIState<List<SatScoresItem>>> = flow {
        emit(UIState.LOADING)
        try {
            Log.d(TAG, "getSat: ")
            val response = schoolsApi.getSatScores(dbn)
            if (response.isSuccessful) {
                response.body()?.let {
                    val temp = UIState.SUCCESS(it)
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