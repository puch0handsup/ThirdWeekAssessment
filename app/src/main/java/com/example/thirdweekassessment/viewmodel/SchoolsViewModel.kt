package com.example.thirdweekassessment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdweekassessment.model.SatScoresItem
import com.example.thirdweekassessment.model.SchoolsItem
import com.example.thirdweekassessment.rest.SchoolsRepository
import com.example.thirdweekassessment.utils.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.log

private const val TAG = "SchoolsViewModel"

class SchoolsViewModel @Inject constructor(
    private val schoolsRepository: SchoolsRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    init {
        getSchools()
    }

    var fragmentState = false

    var selectedItemId: SchoolsItem? = null

    private val _schools: MutableLiveData<UIState<List<SchoolsItem>>> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState<List<SchoolsItem>>> get() = _schools

    private val _satScores: MutableLiveData<UIState<List<SatScoresItem>>> = MutableLiveData(UIState.LOADING)
    val satScores: LiveData<UIState<List<SatScoresItem>>> get() = _satScores

    fun getSchools (dbn: String? = null) {
        dbn?.let {item ->
            viewModelScope.launch(ioDispatcher) {
                schoolsRepository.getSat(item).collect {
                    _satScores.postValue(it)
                }
            }
        } ?: run {
            viewModelScope.launch(ioDispatcher) {
                schoolsRepository.getSchools().collect {
                    _schools.postValue(it)
                }
            }
        }
    }
}