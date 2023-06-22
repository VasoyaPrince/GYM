package com.glory.gym.`interface`

import com.glory.gym.model.AllEquipment
import com.glory.gym.model.AllExercisesItem
import com.glory.gym.model.ListOfEquipment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {

    @Headers(
        value = ["X-RapidAPI-Key:0ee5e449e5mshf8556c8f1121dc6p194203jsnd16515700976", "X-RapidAPI-Host:exercisedb.p.rapidapi.com"]
    )
    @GET("exercises")
    fun allExercises(): Call<List<AllExercisesItem>>

    @Headers(
        value = ["X-RapidAPI-Key:0ee5e449e5mshf8556c8f1121dc6p194203jsnd16515700976", "X-RapidAPI-Host:exercisedb.p.rapidapi.com"]
    )
    @GET("exercises/equipmentList")
    fun allEquipment(): Call<List<String>>

    @Headers(
        value = ["X-RapidAPI-Key:0ee5e449e5mshf8556c8f1121dc6p194203jsnd16515700976", "X-RapidAPI-Host:exercisedb.p.rapidapi.com"]
    )
    @GET("exercises/equipment/{type}")
    fun listOfEquipment(@Path("type") type: String): Call<List<AllExercisesItem>>

    @Headers(
        value = ["X-RapidAPI-Key:0ee5e449e5mshf8556c8f1121dc6p194203jsnd16515700976", "X-RapidAPI-Host:exercisedb.p.rapidapi.com"]
    )
    @GET("exercises/targetList")
    fun allMuscles(): Call<List<String>>

    @Headers(
        value = ["X-RapidAPI-Key:0ee5e449e5mshf8556c8f1121dc6p194203jsnd16515700976", "X-RapidAPI-Host:exercisedb.p.rapidapi.com"]
    )
    @GET("exercises/target/{target}")
    fun listOfTarget(@Path("target") target: String): Call<List<AllExercisesItem>>

}