package com.glory.gym.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glory.gym.databinding.ActivityAccountSettingsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import java.util.EventListener


class AccountSettingsActivity : AppCompatActivity() {
    lateinit var binding: ActivityAccountSettingsBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.deleteAccount.setOnClickListener {
            Firebase.auth.currentUser!!.delete()
            val sh = getSharedPreferences("MySharedPref", MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sh.edit()
            editor.remove("login")
            editor.apply()

            FirebaseDatabase.getInstance().reference.child("Profile").addListenerForSingleValueEvent(
                object : EventListener, ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        snapshot.child(Firebase.auth.uid!!).ref.removeValue()
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

            startActivity(
                Intent(
                    this@AccountSettingsActivity, WelcomeActivity::class.java
                )
            )
            finish()

        }

    }
}