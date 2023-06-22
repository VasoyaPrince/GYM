package com.glory.gym.fragment

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.glory.gym.R
import com.glory.gym.activity.ProfileActivity
import com.glory.gym.activity.SettingsActivity
import com.glory.gym.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    lateinit var barEntriesList: ArrayList<BarEntry>
    lateinit var barData: BarData
    lateinit var barDataSet: BarDataSet
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.editProfile.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileActivity::class.java))
        }
        binding.settings.setOnClickListener {
            startActivity(Intent(requireContext(), SettingsActivity::class.java))
        }

        getBarChartData()

        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")

        barData = BarData(barDataSet)

        binding.idBarChart.data = barData

        barDataSet.valueTextColor = Color.BLACK

        barDataSet.color = resources.getColor(R.color.purple_200)

        barDataSet.valueTextSize = 16f

        binding.idBarChart.description.isEnabled = false

        return binding.root
    }

    private fun getBarChartData() {
        barEntriesList = ArrayList()

        barEntriesList.add(BarEntry(1f, 1f))
        barEntriesList.add(BarEntry(2f, 2f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(4f, 4f))
        barEntriesList.add(BarEntry(5f, 5f))
        barEntriesList.add(BarEntry(6f, 6f))

    }

}