package com.glory.gym.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.glory.gym.R
import com.glory.gym.databinding.ActivityLogWorkoutBinding

class LogWorkoutActivity : AppCompatActivity() {
    lateinit var binding: ActivityLogWorkoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addExercise.setOnClickListener {
            startActivity(Intent(this@LogWorkoutActivity, AddExerciseActivity::class.java))
        }

    }
}