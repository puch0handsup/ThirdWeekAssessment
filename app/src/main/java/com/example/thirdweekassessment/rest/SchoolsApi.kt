package com.example.thirdweekassessment.rest

import com.example.thirdweekassessment.model.SatScoresItem
import com.example.thirdweekassessment.model.SchoolsItem
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SchoolsApi {
    @GET(SCHOOL_PATH)
    suspend fun getAllSchools(): Response<List<SchoolsItem>>

    @GET(SAT_SCORES)
    suspend fun getSatScores(
        @Query("dbn") dbn: String
    ): Response<List<SatScoresItem>>

    // https://data.cityofnewyork.us/resource/s3k6-pzi2.json?dbn=26Q495
    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
        const val SCHOOL_PATH = "s3k6-pzi2.json"
        const val SAT_SCORES = "f9bf-2cp4.json"
    }


}