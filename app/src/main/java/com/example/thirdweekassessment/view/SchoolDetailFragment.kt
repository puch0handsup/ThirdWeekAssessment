package com.example.thirdweekassessment.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thirdweekassessment.R
import com.example.thirdweekassessment.databinding.FragmentSchoolDetailBinding
import com.example.thirdweekassessment.model.SatScoresItem
import com.example.thirdweekassessment.model.SchoolsItem
import com.example.thirdweekassessment.utils.BaseFragment
import com.example.thirdweekassessment.utils.UIState

private const val TAG = "SchoolDetailFragment"
class SchoolDetailFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSchoolDetailBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        schoolsViewModel.satScores.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS<*> -> {
                    Log.d(TAG, "onCreateView: ")
                    val temp = state.response as List<SatScoresItem>
                    binding.schoolName.text = temp.last().schoolName
                    binding.numOfSatTestTakers.text = getString(R.string.number_of_sat_test_takers, temp.last().numOfSatTestTakers)
                    binding.satMathAvgScore.text = getString(R.string.math_avg_score, temp.last().satMathAvgScore)
                    binding.satCriticalReadingAvgScore.text = getString(R.string.critical_reading_avg_score, temp.last().satCriticalReadingAvgScore)
                    binding.satWritingAvgScore.text = getString(R.string.writing_avg_score, temp.last().satWritingAvgScore)
                }
                is UIState.ERROR -> {
                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error occurred")
                        .setMessage(state.error.localizedMessage)
                        .setPositiveButton("RETRY") { dialog, _ ->
                            schoolsViewModel.getSchools()
                            dialog.dismiss()
                        }
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }
        return binding.root
    }


}