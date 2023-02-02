package com.example.thirdweekassessment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.thirdweekassessment.rest.SchoolsRepository
import com.example.thirdweekassessment.utils.UIState
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

//    var fragmentState = false

//    var selectedItemId: String? = null

    private val _schools: MutableLiveData<UIState> = MutableLiveData(UIState.LOADING)
    val schools: LiveData<UIState> get() = _schools

    fun getSchools (id: String? = null) {
        id?.let {
//            viewModelScope.launch(ioDispatcher) {
//                schoolsRepository.getSchoolById(it).collect {
//                    _people.postValue(it)
//                }
//            }
        } ?: run {
            viewModelScope.launch(ioDispatcher) {
                schoolsRepository.getSchools().collect {
                    _schools.postValue(it)
                    Log.d(TAG, "getSchools: $it")
                }
            }
        }
    }
}