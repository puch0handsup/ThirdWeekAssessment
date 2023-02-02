package com.example.thirdweekassessment.di

import com.example.thirdweekassessment.MainActivity
import com.example.thirdweekassessment.utils.BaseFragment
import dagger.Component


@Component(modules = [
    NetworkModule::class,
    RepositoryModule::class,
    ApplicationModule::class
])
interface SchoolsComponent {
    fun inject(MainActivity: MainActivity)
    fun inject(BaseFragment: BaseFragment)
}