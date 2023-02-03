package com.example.thirdweekassessment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstnetworkapi.adapter.SchoolAdapter
import com.example.thirdweekassessment.model.SchoolsItem
import com.example.thirdweekassessment.rest.SchoolsRepository
import com.example.thirdweekassessment.utils.UIState
import com.example.thirdweekassessment.utils.UIState2
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

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

    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    private val _satScores: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val satScores: LiveData<UIState> get() = _satScores

    fun getSchools (dbn: String? = null) {
        dbn?.let {item ->
            Log.d(TAG, "getSchools: ENTERED IN getSchools(${item})")
            viewModelScope.launch(ioDispatcher) {
                schoolsRepository.getSat(item).collect {
                    _satScores.postValue(it)
                }
            }
        } ?: run {
            viewModelScope.launch(ioDispatcher) {
                schoolsRepository.getSchools().collect {
                    Log.d(TAG, "getSchools: UIState Collected: $it")
                    _schools.postValue(it)
                }
            }
        }
    }
}