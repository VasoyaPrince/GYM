package com.glory.gym.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.glory.gym.activity.AddExerciseActivity
import com.glory.gym.databinding.BottomsheetLayoutBinding
import com.glory.gym.databinding.BottomshetCardviewBinding
import com.glory.gym.databinding.ExrciseCardviewBinding
import com.glory.gym.model.AllEquipment
import com.glory.gym.model.AllExercisesItem


class AllEquipmentAdapter(
    private val mList: List<String>,
    private val context: Context,
    val onclick: (item: String) -> Unit
) : RecyclerView.Adapter<AllEquipmentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            BottomshetCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val items = mList[position]

        holder.binding.title.text = items
        holder.binding.cardView.setOnClickListener {
            holder.binding.check.visibility = View.VISIBLE
            onclick(items)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: BottomshetCardviewBinding) : RecyclerView.ViewHolder(ItemView.root) {
        val binding = ItemView
    }
}