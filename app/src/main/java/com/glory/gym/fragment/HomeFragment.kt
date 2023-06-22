package com.glory.gym.fragment

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.glory.gym.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
            binding = FragmentHomeBinding.inflate(inflater,container,false)

        val languages = resources.getStringArray(com.glory.gym.R.array.programming_languages)

        val arrayAdapter = ArrayAdapter(requireContext(),R.layout.simple_dropdown_item_1line, languages)
        //binding.autoCompleteTextView.setAdapter(arrayAdapter)

        return binding.root
    }

}

