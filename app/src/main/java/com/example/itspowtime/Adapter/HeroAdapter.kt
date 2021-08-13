package com.example.itspowtime.Adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.bumptech.glide.Glide
import com.example.itspowtime.*
import com.example.itspowtime.Fav.User
import com.example.itspowtime.Fav.UserDatabase
import kotlinx.android.synthetic.main.hero_item.view.*

var ID = 0

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

                for(i in 0..550){
                    if(imageS[i] == images[position])
                        ID = i
                    Log.d("identity",i.toString())
                }
                val intent = Intent(context, HeroDetails::class.java)
                intent.putExtra("Position",ID)
                val options = ViewCompat.getTransitionName(img)?.let {
                    ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity , img,
                        it
                    )
                }
                if (options != null) {
                    context.startActivity(intent, options.toBundle())
                }

            }
        }
    }
    private fun insertData() {

        val user = User(0, nameS[ID])
        val myRoomDatabase = Room.databaseBuilder(context,UserDatabase::class.java,"favs").build()
        myRoomDatabase.userDao().addUser(user)
        Toast.makeText(context,"Added ${nameS[ID]}", Toast.LENGTH_SHORT).show()
    }


}