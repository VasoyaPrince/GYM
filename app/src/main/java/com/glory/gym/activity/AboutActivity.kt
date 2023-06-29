package com.glory.gym.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glory.gym.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {
    lateinit var binding: ActivityAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}