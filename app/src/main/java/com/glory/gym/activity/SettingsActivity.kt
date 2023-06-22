package com.glory.gym.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glory.gym.R
import com.glory.gym.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}












