package com.example.thirdweekassessment.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thirdweekassessment.R
import com.example.thirdweekassessment.databinding.FragmentSchoolDetailBinding


class SchoolDetailFragment : Fragment() {

    private val binding by lazy {
        FragmentSchoolDetailBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return binding.root
    }
}