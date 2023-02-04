package com.example.thirdweekassessment.view

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firstnetworkapi.adapter.SchoolAdapter
import com.example.thirdweekassessment.R
import com.example.thirdweekassessment.databinding.FragmentSchoolListBinding
import com.example.thirdweekassessment.model.SchoolsItem
import com.example.thirdweekassessment.utils.BaseFragment
import com.example.thirdweekassessment.utils.UIState

private const val TAG = "SchoolListFragment"
class SchoolListFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSchoolListBinding.inflate(layoutInflater)
    }

    private val schoolAdapter by lazy {
        SchoolAdapter {
            Log.d(TAG, "SchoolAdapter on Clicked: it = ${it.dbn}")
            schoolsViewModel.getSchools(it.dbn)
            findNavController().navigate(R.id.action_SchoolsFragment_to_DetailsFragment)
        }
    }

    private var tempList : List<SchoolsItem> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding.schoolRv.apply {
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = schoolAdapter
        }

        schoolsViewModel.schools.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS<*> -> {
                    schoolAdapter.updateSchools(state.response as List<SchoolsItem>)
                    tempList = state.response
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
        binding.searchView.clearFocus()
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return true
            }
        })

        return binding.root
    }

    private fun filterList(text: String) {
        val filteredList: MutableList<SchoolsItem> = mutableListOf()
        tempList.forEach {
            if (it.schoolName?.lowercase()!!.contains(text.lowercase())) {
                filteredList.add(it)
            }
        }

        if (filteredList.isEmpty()) {
            Toast.makeText(context, "No schools found", Toast.LENGTH_SHORT).show()
        }else{
            schoolAdapter.setFilteredList(filteredList )
        }
    }
}