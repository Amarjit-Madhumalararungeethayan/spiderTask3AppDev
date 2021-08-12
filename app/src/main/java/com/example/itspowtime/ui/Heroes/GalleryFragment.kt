package com.example.itspowtime.ui.Heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.itspowtime.Adapter.HeroAdapter
import com.example.itspowtime.alignmentS
import com.example.itspowtime.databinding.FragmentGalleryBinding
import com.example.itspowtime.imageS
import java.util.ArrayList

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: GalleryViewModel
    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        var heroesImages: ArrayList<String> = ArrayList()

        for(i in 0..550){
            if(alignmentS[i] == "good"){
                heroesImages.add(imageS[i])
            }
        }
        val adapter = context?.let { HeroAdapter(it, heroesImages) }


        // Layout as Staggered Grid for vertical orientation
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL)
        binding.heroesRV.layoutManager = staggeredGridLayoutManager

        // Adapter to RecyclerView
        binding.heroesRV.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}