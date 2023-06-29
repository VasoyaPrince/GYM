package com.glory.gym.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glory.gym.R
import com.glory.gym.databinding.ActivityExerciseDetalisBinding

class ExerciseDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityExerciseDetalisBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseDetalisBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}