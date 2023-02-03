package com.example.thirdweekassessment.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SatScoresItem(
    @Json(name = "dbn")
    val dbn: String? = null,
    @Json(name = "num_of_sat_test_takers")
    val numOfSatTestTakers: String? = null,
    @Json(name = "sat_critical_reading_avg_score")
    val satCriticalReadingAvgScore: String? = null,
    @Json(name = "sat_math_avg_score")
    val satMathAvgScore: String? = null,
    @Json(name = "sat_writing_avg_score")
    val satWritingAvgScore: String? = null,
    @Json(name = "school_name")
    val schoolName: String? = null
)