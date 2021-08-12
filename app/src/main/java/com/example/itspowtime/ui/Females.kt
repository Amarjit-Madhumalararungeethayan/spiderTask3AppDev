package com.example.itspowtime.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.itspowtime.Adapter.HeroAdapter
import com.example.itspowtime.R
import com.example.itspowtime.alignmentS
import com.example.itspowtime.databinding.FemalesFragmentBinding
import com.example.itspowtime.databinding.FragmentGalleryBinding
import com.example.itspowtime.genderS
import com.example.itspowtime.imageS
import com.example.itspowtime.ui.Heroes.GalleryViewModel
import java.util.ArrayList

class Females : Fragment() {

    private var _binding: FemalesFragmentBinding? = null
    private lateinit var viewModel: FemalesViewModel

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(FemalesViewModel::class.java)

        _binding = FemalesFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var femaleImages: ArrayList<String> = ArrayList()

        for(i in 0..550){
            if(genderS[i] == "Female"){
                femaleImages.add(imageS[i])
            }
        }
        val adapter = context?.let { HeroAdapter(it, femaleImages) }


        // Layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.femalesRV.layoutManager = staggeredGridLayoutManager

        // Adapter to RecyclerView
        binding.femalesRV.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}