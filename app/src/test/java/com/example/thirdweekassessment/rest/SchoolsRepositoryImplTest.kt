package com.example.thirdweekassessment.rest


import com.example.thirdweekassessment.model.SatScoresItem
import com.example.thirdweekassessment.model.SchoolsItem
import com.example.thirdweekassessment.utils.UIState
import com.google.common.truth.Truth.assertThat
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test

class SchoolsRepositoryImplTest {

    private lateinit var testObject: SchoolsRepository

    private val mockSchoolsApi: SchoolsApi = mockk(relaxed = true)

    @Before
    fun setUp() {
        testObject = SchoolsRepositoryImpl(mockSchoolsApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `get the list of all schools from @GET request, should return a success state`() =
        runTest {
            // AAA
            // ASSIGNMENT
            coEvery { mockSchoolsApi.getAllSchools() } returns mockk {
                every { isSuccessful } returns true
                every { body() } returns listOf(mockk(), mockk())
            }
            val states = mutableListOf<UIState<List<SchoolsItem>>>()

            // ACTION
            testObject.getSchools().collect {
                states.add(it)
            }

            // ASSERTION
            assertThat(states).hasSize(2)
            assertThat(states.first()).isInstanceOf(UIState.LOADING::class.java)
            assertThat(states[1]).isInstanceOf(UIState.SUCCESS::class.java)
            assertThat(
                (states[1] as UIState.SUCCESS<List<SchoolsItem>>).response
            ).hasSize(2)
        }

    @Test
    fun `get one school's SAT scores when providing the dbn from adapter`() =
        runTest {
            // AAA
            // ASSIGNMENT
            coEvery { mockSchoolsApi.getSatScores("06M540") } returns mockk {
                every { isSuccessful } returns true
                every { body() } returns listOf(mockk())
            }
            val states = mutableListOf<UIState<List<SatScoresItem>>>()

            // ACTION
            testObject.getSat("").collect {
                states.add(it)
            }

            // ASSERTION
            assertThat(states).hasSize(2)
            assertThat(states.first()).isInstanceOf(UIState.LOADING::class.java)
            assertThat(states[1]).isInstanceOf(UIState.SUCCESS::class.java)
            assertThat(
                (states[1] as UIState.SUCCESS<List<SatScoresItem>>).response
            ).hasSize(2)
        }
}