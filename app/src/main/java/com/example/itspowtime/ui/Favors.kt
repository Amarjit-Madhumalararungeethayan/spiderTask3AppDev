package com.example.itspowtime.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.room.Room
import com.example.itspowtime.*
import com.example.itspowtime.Adapter.HeroAdapter
import com.example.itspowtime.Fav.UserDatabase
import com.example.itspowtime.databinding.FavorsFragmentBinding
import com.example.itspowtime.databinding.FemalesFragmentBinding
import java.util.ArrayList

class Favors : Fragment() {

    private var _binding: FavorsFragmentBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: FavorsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(FavorsViewModel::class.java)

        _binding = FavorsFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = context?.let { HeroAdapter(it, favImages) }


        // Layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.favorsRV.layoutManager = staggeredGridLayoutManager

        // Adapter to RecyclerView
        binding.favorsRV.adapter = adapter


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}