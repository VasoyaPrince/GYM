package com.glory.gym.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glory.gym.databinding.ActivityUnitsBinding

class UnitsActivity : AppCompatActivity() {
    lateinit var binding: ActivityUnitsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnitsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}