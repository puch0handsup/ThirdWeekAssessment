package com.example.thirdweekassessment.di

import android.app.Application
import android.content.Context
import com.example.thirdweekassessment.rest.SchoolsRepository
import com.example.thirdweekassessment.utils.SchoolsViewModelFactory
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher


@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun providesContext(): Context =
        application.applicationContext

    @Provides
    fun providesViewModelFactory(
        repository: SchoolsRepository,
        ioDispatcher: CoroutineDispatcher
    ): SchoolsViewModelFactory = SchoolsViewModelFactory(repository, ioDispatcher)
}