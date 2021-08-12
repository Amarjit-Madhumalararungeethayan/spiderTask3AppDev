package com.example.itspowtime.ui.Villains

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.itspowtime.Adapter.HeroAdapter
import com.example.itspowtime.alignmentS
import com.example.itspowtime.databinding.FragmentSlideshowBinding
import com.example.itspowtime.imageS
import java.util.ArrayList

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var villainsImages: ArrayList<String> = ArrayList()

        for(i in 0..550){
            if(alignmentS[i] == "bad"){
                villainsImages.add(imageS[i])
            }
        }
        val adapter = context?.let { HeroAdapter(it, villainsImages) }


        // Layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.villainsRV.layoutManager = staggeredGridLayoutManager

        // Adapter to RecyclerView
        binding.villainsRV.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}