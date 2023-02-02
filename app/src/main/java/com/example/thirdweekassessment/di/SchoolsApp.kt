package com.example.thirdweekassessment.di

import android.app.Application

class SchoolsApp: Application() {
    companion object {
        lateinit var schoolsComponent: SchoolsComponent

    }
    override fun onCreate() {
        super.onCreate()

        schoolsComponent = DaggerSchoolsComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}