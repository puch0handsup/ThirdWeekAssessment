package com.example.firstnetworkapi.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.thirdweekassessment.databinding.LetterItemBinding
import com.example.thirdweekassessment.databinding.SchoolItemBinding
import com.example.thirdweekassessment.model.SchoolsItem

private const val TAG = "SchoolAdapter"

class SchoolAdapter(
    private val schoolSet: MutableList<ViewType> = mutableListOf(),
    private val onClickedSchool: (SchoolsItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var schoolClicked : SchoolsItem? = null

    fun updateSchools(newSchools: List<SchoolsItem>) {
        var tempChar = '+'
        newSchools.sortedBy { it.schoolName }.forEach { school ->
            val firstLetter = school.schoolName?.first() ?: '+'
            if (firstLetter != tempChar) {
                schoolSet.add(ViewType.LETTER(firstLetter.toString()))
                tempChar = firstLetter
            }
            schoolSet.add(ViewType.SCHOOL(school))
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            LetterViewHolder(
                LetterItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            SchoolViewHolder(
                SchoolItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(val item = schoolSet[position]) {
            is ViewType.SCHOOL -> {
                (holder as SchoolViewHolder).schoolBinding(item.schoolItem, onClickedSchool)
                schoolClicked = item.schoolItem
            }
            is ViewType.LETTER -> {
                (holder as LetterViewHolder).bindLetter(item.letter)
            }
        }
    }

    override fun getItemCount(): Int = schoolSet.size

    override fun getItemViewType(position: Int): Int {
        return when(schoolSet[position]) {
            is ViewType.SCHOOL -> 1
            is ViewType.LETTER -> 0
        }
    }

    fun setFilteredList(filteredList: List<SchoolsItem>) {
        schoolSet.clear()
        updateSchools(filteredList)
        Log.d(TAG, "setFilteredList: notified Dataset Changed")
    }
}

class SchoolViewHolder(
    private val binding: SchoolItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun schoolBinding(school: SchoolsItem, onClickedSchool: (SchoolsItem) -> Unit) {
        binding.schoolName.text = school.schoolName
        binding.schoolAddress.text = school.location
        binding.schoolPhone.text = school.phoneNumber

        binding.openGoogleMaps.setOnClickListener {
            val latitude = school.location?.substringAfterLast("(")?.substringBefore(",")
            val longitude = school.location?.substringAfterLast(" ")?.substringBefore(")")
            google_maps_redirect(latitude, longitude, school.location, it.context)
        }
        binding.moreBtn.setOnClickListener {
            onClickedSchool(school)
        }
    }

    private fun google_maps_redirect(latitude: String?, longitude: String?, location: String?, context: Context) {
        val url = "http://maps.google.com/maps?q=$latitude,$longitude($location)&iwloc=A&hl=es"
        val mapsIntentUri = Uri.parse(url)
        val mapIntent = Intent(Intent.ACTION_VIEW, mapsIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        context.startActivity(mapIntent)
    }
}

class LetterViewHolder(
    private val binding: LetterItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindLetter(letter: String) {
        binding.letterName.text = letter
    }
}