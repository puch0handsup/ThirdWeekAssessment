package com.example.thirdweekassessment.di

import com.example.thirdweekassessment.rest.SchoolsRepository
import com.example.thirdweekassessment.rest.SchoolsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun providesSchoolRepository(
        schoolsRepositoryImpl: SchoolsRepositoryImpl?
    ) : SchoolsRepository
}