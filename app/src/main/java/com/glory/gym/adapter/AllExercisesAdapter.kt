package com.glory.gym.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.glory.gym.databinding.ExrciseCardviewBinding
import com.glory.gym.model.AllExercisesItem


class AllExercisesAdapter(
    private val mList: List<AllExercisesItem>,
    private val context: Context,
) : RecyclerView.Adapter<AllExercisesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ExrciseCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val items = mList[position]

        holder.binding.title.text = items.name
        holder.binding.desc.text = items.bodyPart
        Glide.with(context).load(items.gifUrl).into(holder.binding.image)
        holder.binding.cardView.setOnClickListener {
            val intent = Intent()
            intent.putExtra("image", items.gifUrl)
            intent.putExtra("name", items.name)
            intent.putExtra("subtitle", items.bodyPart)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: ExrciseCardviewBinding) : RecyclerView.ViewHolder(ItemView.root) {
        val binding = ItemView
    }
}