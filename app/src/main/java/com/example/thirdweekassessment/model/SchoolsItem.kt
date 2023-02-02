package com.example.thirdweekassessment.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SchoolsItem(

    @Json(name = "city")
    val city: String? = null,
    @Json(name = "dbn")
    val dbn: String? = null,
    @Json(name = "location")
    val location: String? = null,
    @Json(name = "latitude")
    val latitude: String? = null,
    @Json(name = "longitude")
    val longitude: String? = null,
    @Json(name = "overview_paragraph")
    val overviewParagraph: String? = null,
    @Json(name = "school_email")
    val schoolEmail: String? = null,
    @Json(name = "school_name")
    val schoolName: String? = null,
    @Json(name = "phone_number")
    val phoneNumber: String? = null,
    @Json(name = "state_code")
    val stateCode: String? = null,
    @Json(name = "subway")
    val subway: String? = null,
    @Json(name = "total_students")
    val totalStudents: String? = null,
    @Json(name = "transfer")
    val transfer: String? = null,
    @Json(name = "website")
    val website: String? = null,
    @Json(name = "zip")
    val zip: String? = null

)