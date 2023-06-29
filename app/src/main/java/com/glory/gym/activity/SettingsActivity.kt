package com.glory.gym.activity


import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glory.gym.databinding.ActivitySettingsBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingsBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        auth = Firebase.auth

        binding.profile.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, ProfileActivity::class.java))
        }

        binding.logout.setOnClickListener {
            auth.signOut()
            val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sh.edit()
            editor.remove("login")
            editor.apply()
            startActivity(Intent(this@SettingsActivity, WelcomeActivity::class.java))
        }

        binding.account.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, AccountSettingsActivity::class.java))
        }
        binding.Units.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, UnitsActivity::class.java))
        }

        binding.about.setOnClickListener {
            startActivity(Intent(this@SettingsActivity, AboutActivity::class.java))
        }

    }
}












