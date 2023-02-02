package com.example.thirdweekassessment.utils


class NullSchoolsResponse(message: String = "Schools response is null") : Exception(message)
class FailureResponse(message: String?) : Exception(message)