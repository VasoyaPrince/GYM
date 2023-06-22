package com.glory.gym.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.glory.gym.R
import com.glory.gym.databinding.ActivityWalcomeBinding

class WelcomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityWalcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signUp.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity,SignUpActivity::class.java))
        }

        binding.signIn.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity,SignInActivity::class.java))
        }

    }
}