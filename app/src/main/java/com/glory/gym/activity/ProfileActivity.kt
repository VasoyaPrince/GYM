package com.glory.gym.activity

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.glory.gym.databinding.ActivityProfileBinding
import com.glory.gym.model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.util.UUID


class ProfileActivity : AppCompatActivity() {
    lateinit var binding: ActivityProfileBinding
    private var fileUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val user1 = Firebase.auth.currentUser
        val database = Firebase.database
        val myRef = database.getReference("Profile")
        val database11 = FirebaseDatabase.getInstance().reference.child("Profile")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val map = dataSnapshot.value as Map<String, Any>?
//                    val value = dataSnapshot.getValue(String::class.java)
                    Glide.with(this@ProfileActivity).load(map).into(binding.avatar)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })

        if (myRef != null) {
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val value = dataSnapshot.value
                    val data = dataSnapshot.child("${user1?.uid}").getValue<User>()
                    Log.d("TAG", "Value is: $data")
                    Log.d("TAG", "Value is: $value")
                    binding.Bio.setText(data?.Bio)
                    binding.birthday.setText(data?.Birthday)
                    binding.gender.setText(data?.Gender)
                    binding.link.setText(data?.Link)
                    binding.name.setText(data?.name)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("TAG", "Failed to read value.", error.toException())
                }
            })
        }

        val firebaseDatabase = Firebase.storage
        val databaseReference = firebaseDatabase.reference
        val getImage = databaseReference.child("Profile")
        val pic = Firebase.storage.getReference("Profile")
        Glide.with(this@ProfileActivity).load(pic).into(binding.avatar)


        binding.changePicture.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    intent, "Pick your image to upload"
                ), 22
            )
        }

        binding.save.setOnClickListener {
            val user1 = Firebase.auth.currentUser
            user1?.let {
                val email = it.email
                uploadImage()
                val uid = it.uid

                val database = Firebase.database
                val myRef = database.getReference("Profile")

                val user = myRef.child("$uid")

                val pic = user.child("picture")
                pic.setValue("$fileUri")

                val name = user.child("name")
                name.setValue("${binding.name.text}")

                val bio = user.child("Bio")
                binding.Bio.text.toString()
                bio.setValue("${binding.Bio.text}")

                val link = user.child("Link")
                link.setValue("${binding.link.text}")

                val sex = user.child("Gender")
                sex.setValue("${binding.gender.text}")

                val birthday = user.child("Birthday")
                birthday.setValue("${binding.birthday.text}")

                val email1 = user.child("Email")
                email1.setValue("$email")
            }
        }

    }

    private fun uploadImage() {
        if (fileUri != null) {

            val storage = FirebaseStorage.getInstance()
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.setMessage("Uploading your image..")
            progressDialog.show()
            val ref: StorageReference = FirebaseStorage.getInstance().reference.child("Profile")
                .child(UUID.randomUUID().toString())

            ref.putFile(fileUri!!).addOnSuccessListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Image Uploaded..", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                progressDialog.dismiss()
                Toast.makeText(applicationContext, "Fail to Upload Image..", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 22 && resultCode == RESULT_OK && data != null && data.data != null) {
            fileUri = data.data
            try {
                val bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, fileUri);
                binding.avatar.setImageBitmap(bitmap)
            } catch (e: Exception) {

                e.printStackTrace()
            }
        }
    }
}