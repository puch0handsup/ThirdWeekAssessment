package com.example.thirdweekassessment.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thirdweekassessment.databinding.FragmentSchoolListBinding
import com.example.thirdweekassessment.utils.BaseFragment
import com.example.thirdweekassessment.utils.UIState


class SchoolListFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSchoolListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        schoolsViewModel.schools.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {}
                is UIState.ERROR -> {}
            }
        }
        return binding.root
    }
}