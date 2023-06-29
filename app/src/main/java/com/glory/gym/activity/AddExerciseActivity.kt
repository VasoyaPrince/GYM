package com.glory.gym.activity

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.glory.gym.R
import com.glory.gym.adapter.AllEquipmentAdapter
import com.glory.gym.adapter.AllExercisesAdapter
import com.glory.gym.databinding.ActivityAddExerciseBinding
import com.glory.gym.`interface`.ApiService
import com.glory.gym.model.AllEquipment
import com.glory.gym.model.AllExercisesItem
import com.glory.gym.model.ListOfEquipment
import com.glory.gym.service.RetrofitAPIClient
import com.google.android.material.bottomsheet.BottomSheetDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddExerciseActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddExerciseBinding
    private lateinit var retrofitAPIClient: RetrofitAPIClient
    lateinit var adapter: AllExercisesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitAPIClient = RetrofitAPIClient(this@AddExerciseActivity)
        val retrofit = retrofitAPIClient.getRetrofitClient()!!
        val retrofitApi: ApiService = retrofit.create(ApiService::class.java)

        binding.back.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.noEquipment.setOnClickListener {
            val dialog = BottomSheetDialog(this@AddExerciseActivity)

            val view = layoutInflater.inflate(R.layout.bottomsheet_layout, null)
            val cancel = view.findViewById<TextView>(R.id.cancal)
            val cardView = view.findViewById<RecyclerView>(R.id.recyclerView1)
            val title = view.findViewById<TextView>(R.id.textView19)
            val title1 = view.findViewById<TextView>(R.id.title)
            title.text = "Equipment"
            title1.text = "All Equipment"
            val call = retrofitApi.allEquipment()
            call.enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        val adapter2 = AllEquipmentAdapter(
                            response.body()!!, this@AddExerciseActivity
                        ) { item ->
                            binding.noEquipment.text = "$item"
                            binding.close.visibility = View.VISIBLE
                            dialog.dismiss()
                            val call1: Call<List<AllExercisesItem>> =
                                retrofitApi.listOfEquipment(item)

                            call1.enqueue(object : Callback<List<AllExercisesItem>> {
                                override fun onResponse(
                                    call: Call<List<AllExercisesItem>>,
                                    response: Response<List<AllExercisesItem>>
                                ) {
                                    if (response.isSuccessful) {
                                        adapter = AllExercisesAdapter(
                                            response.body()!!, this@AddExerciseActivity
                                        )
                                    }
                                    binding.recylerview.adapter = adapter
                                }

                                override fun onFailure(
                                    call: Call<List<AllExercisesItem>>, t: Throwable
                                ) {
                                    Toast.makeText(
                                        this@AddExerciseActivity, "${t.message}", Toast.LENGTH_SHORT
                                    ).show()
                                }

                            })
                        }
                        cardView.adapter = adapter2
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Toast.makeText(this@AddExerciseActivity, "${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }

            })

            cancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }


        binding.allMuscles.setOnClickListener {

            val dialog = BottomSheetDialog(this@AddExerciseActivity)
            val view = layoutInflater.inflate(R.layout.bottomsheet_layout, null)
            val cancel = view.findViewById<TextView>(R.id.cancal)
            val title = view.findViewById<TextView>(R.id.textView19)
            val title1 = view.findViewById<TextView>(R.id.title)
            val cardView = view.findViewById<RecyclerView>(R.id.recyclerView1)
            title.text = "Muscles"
            title1.text = "All Muscles"
            val call = retrofitApi.allMuscles()
            call.enqueue(object : Callback<List<String>> {
                override fun onResponse(
                    call: Call<List<String>>, response: Response<List<String>>
                ) {
                    if (response.isSuccessful) {
                        val adapter2 = AllEquipmentAdapter(
                            response.body()!!, this@AddExerciseActivity
                        ) { item ->
                            binding.allMuscles.text = "$item"
                            binding.close.visibility = View.VISIBLE
                            dialog.dismiss()
                            val call1: Call<List<AllExercisesItem>> =
                                retrofitApi.listOfTarget(item)

                            call1.enqueue(object : Callback<List<AllExercisesItem>> {
                                override fun onResponse(
                                    call: Call<List<AllExercisesItem>>,
                                    response: Response<List<AllExercisesItem>>
                                ) {
                                    if (response.isSuccessful) {
                                        adapter = AllExercisesAdapter(
                                            response.body()!!, this@AddExerciseActivity
                                        )
                                    }
                                    binding.recylerview.adapter = adapter
                                }

                                override fun onFailure(
                                    call: Call<List<AllExercisesItem>>, t: Throwable
                                ) {
                                    Toast.makeText(
                                        this@AddExerciseActivity, "${t.message}", Toast.LENGTH_SHORT
                                    ).show()
                                }

                            })
                        }
                        cardView.adapter = adapter2
                    }
                }

                override fun onFailure(call: Call<List<String>>, t: Throwable) {
                    Toast.makeText(this@AddExerciseActivity, "${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }

            })

            cancel.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }

        val call: Call<List<AllExercisesItem>> = retrofitApi.allExercises()

        call.enqueue(object : Callback<List<AllExercisesItem>> {
            override fun onResponse(
                call: Call<List<AllExercisesItem>>, response: Response<List<AllExercisesItem>>
            ) {
                if (response.isSuccessful) {
                    adapter = AllExercisesAdapter(response.body()!!, this@AddExerciseActivity)
                }
                binding.recylerview.adapter = adapter
            }

            override fun onFailure(call: Call<List<AllExercisesItem>>, t: Throwable) {
                Toast.makeText(this@AddExerciseActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }

        })

        binding.close.setOnClickListener {
            binding.close.visibility = View.GONE
            val call: Call<List<AllExercisesItem>> = retrofitApi.allExercises()

            call.enqueue(object : Callback<List<AllExercisesItem>> {
                override fun onResponse(
                    call: Call<List<AllExercisesItem>>, response: Response<List<AllExercisesItem>>
                ) {
                    if (response.isSuccessful) {
                        adapter = AllExercisesAdapter(response.body()!!, this@AddExerciseActivity)
                    }
                    binding.recylerview.adapter = adapter
                }

                override fun onFailure(call: Call<List<AllExercisesItem>>, t: Throwable) {
                    Toast.makeText(this@AddExerciseActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }

            })
        }

    }
}