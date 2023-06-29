package com.glory.gym.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glory.gym.databinding.ActivityWalcomeBinding


class WelcomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityWalcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWalcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val s1 = sh.getBoolean("login", false)

        if (s1) {
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            finish()
        }

        binding.signUp.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, SignUpActivity::class.java))
        }

        binding.signIn.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, SignInActivity::class.java))
        }

    }

}