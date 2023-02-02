package com.example.thirdweekassessment.rest

import com.example.thirdweekassessment.model.SchoolsItem
import retrofit2.Response
import retrofit2.http.GET

interface SchoolsApi {
    @GET(SCHOOL_PATH)
    suspend fun getAllSchools(): Response<List<SchoolsItem>>

    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        const val SCHOOL_PATH = "s3k6-pzi2.json"
    }
}