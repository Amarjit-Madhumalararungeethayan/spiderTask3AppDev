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
import com.example.itspowtime.databinding.FemalesFragmentBinding
import com.example.itspowtime.databinding.MaleFragmentBinding
import com.example.itspowtime.genderS
import com.example.itspowtime.imageS
import java.util.ArrayList

class Male : Fragment() {

    private var _binding: MaleFragmentBinding? = null
    private lateinit var viewModel: MaleViewModel

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(MaleViewModel::class.java)

        _binding = MaleFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var maleImages: ArrayList<String> = ArrayList()

        for(i in 0..550){
            if(genderS[i] == "Male"){
                maleImages.add(imageS[i])
            }
        }
        val adapter = context?.let { HeroAdapter(it, maleImages) }


        // Layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.malesRV.layoutManager = staggeredGridLayoutManager

        // Adapter to RecyclerView
        binding.malesRV.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}