package com.example.itspowtime.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.itspowtime.HeroGallery
import com.example.itspowtime.HeroSearch
import com.example.itspowtime.R
import com.example.itspowtime.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        var comicimages = listOf(R.drawable.m1,R.drawable.m2,R.drawable.m3,R.drawable.m4,R.drawable.m5,R.drawable.m6,R.drawable.m7,R.drawable.m8,R.drawable.m9,R.drawable.m10,R.drawable.m11,R.drawable.m12,R.drawable.m13)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.button3.setOnClickListener(){
            val intent = Intent(context, HeroGallery::class.java)
            startActivity(intent)
        }

        binding.button4.setOnClickListener(){
            val intent = Intent(context, HeroSearch::class.java)
            startActivity(intent)
        }

        binding.imC.setOnClickListener(){
            var ran = (0..12).random()
            binding.imC.setImageResource(comicimages[ran])
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}