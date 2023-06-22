package com.glory.gym.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glory.gym.R
import com.glory.gym.databinding.ActivityExerciseViewBinding

class ExerciseViewActivity : AppCompatActivity() {
    lateinit var binding: ActivityExerciseViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseViewBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}