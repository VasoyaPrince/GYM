package com.glory.gym.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.glory.gym.R
import com.glory.gym.activity.LogWorkoutActivity
import com.glory.gym.databinding.FragmentWorkoutBinding


class WorkoutFragment : Fragment() {
    lateinit var binding: FragmentWorkoutBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutBinding.inflate(inflater, container, false)

        binding.startEmptyWork.setOnClickListener {
            startActivity(Intent(requireContext(), LogWorkoutActivity::class.java))
        }

        return binding.root
    }
}