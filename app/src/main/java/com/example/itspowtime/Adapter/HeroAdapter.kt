package com.example.itspowtime.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.itspowtime.HeroDetails
import com.example.itspowtime.R
import kotlinx.android.synthetic.main.hero_item.view.*

class HeroAdapter(context : Context, images: ArrayList<String>) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    var images: ArrayList<String> = images
    var context: Context

    init {
        this.images = images
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflating the Layout(Instantiates rv_item.xml layout file into View object)
        val view: View = LayoutInflater.from(context).inflate(R.layout.hero_item, parent, false)

        // Passing view to ViewHolder
        return ViewHolder(view)
    }
    // Binding data to the into specified position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.d("image","${images[position]}")

        Glide.with(this.context)
            .load(images[position])
            .override(500, 500)
            .fitCenter()
            .into(holder.img)
    }

    override fun getItemCount():   Int {
        // Returns number of items currently available in Adapter
        return images.size
    }

    // Initializing the Views
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img : ImageView = view.findViewById(R.id.imageView)

        init  {
            view.imageView.setOnClickListener {  view : View ->
                val intent = Intent(context, HeroDetails::class.java)
                intent.putExtra("Position",position)
                context.startActivity(intent)
            }
        }

    }



}