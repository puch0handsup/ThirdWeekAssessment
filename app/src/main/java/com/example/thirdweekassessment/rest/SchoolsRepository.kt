package com.example.thirdweekassessment.rest

import com.example.thirdweekassessment.utils.FailureResponse
import com.example.thirdweekassessment.utils.NullSchoolsResponse
import com.example.thirdweekassessment.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

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

                } ?: throw NullSchoolsResponse()
            } else {
                throw FailureResponse(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }

}