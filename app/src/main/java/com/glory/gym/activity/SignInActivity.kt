package com.glory.gym.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.glory.gym.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        binding.submit.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser

                    val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
                    val myEdit = sharedPreferences.edit()
                    myEdit.putBoolean("login", true)
                    if (user != null) {
                        myEdit.putString("name", user.displayName)
                        Log.d("TAG", "onCreate: ${user.displayName}")
                    }
                    myEdit.apply()
                    Toast.makeText(
                        this@SignInActivity,
                        "Login successfully !!",
                        Toast.LENGTH_SHORT,
                    ).show()
                    startActivity(Intent(this@SignInActivity, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        }

    }
}