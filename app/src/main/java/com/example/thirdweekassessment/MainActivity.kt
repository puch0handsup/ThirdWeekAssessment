package com.example.thirdweekassessment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thirdweekassessment.di.SchoolsApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SchoolsApp.schoolsComponent.inject(this)
    }
}