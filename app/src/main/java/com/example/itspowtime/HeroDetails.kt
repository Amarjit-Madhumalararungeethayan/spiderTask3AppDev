package com.example.itspowtime

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.example.itspowtime.databinding.ActivityHeroDetails2Binding
import kotlinx.android.synthetic.main.activity_hero_details2.*
import java.io.ByteArrayOutputStream

class HeroDetails : AppCompatActivity() {

    lateinit var binding: ActivityHeroDetails2Binding

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
        finishAffinity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHeroDetails2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val position = intent.getIntExtra("Position", 0)

        Glide.with(this)
            .load(imagesX[position])
            .fitCenter()
            .into(binding.imageView3)


        if(nameS[position] != null){
            binding.textView3.text = "Name : ${fNameX[position]}"
        }
        else{
            binding.textView3.text = "Unknown"
        }
            binding.i1.text = "Alias : ${nameS[position]}"
            binding.i2.text = "Alignment : ${alignmentX[position]}"
            binding.i3.text = "Intelligence : ${p1X[position]}    Strength : ${p2X[position]}"
            binding.i4.text = "Speed : ${p3X[position]}           Durability: ${p4X[position]}"
            binding.i5.text = "Power : ${p5X[position]}           Combat : ${p6X[position]}"
            binding.i6.text = "First Appearance : ${fAX[position]}"
            binding.i7.text = "If you like this check out : https://www.marvel.com"


        binding.back.setOnClickListener(){
            super.onBackPressed()
        }

        binding.textView.setOnClickListener(){
            super.onBackPressed()
        }

        binding.share.setOnClickListener(){

            val img = getBitmapFromTheView(imageView3)
            val intent = Intent(Intent.ACTION_SEND)

            intent.putExtra(
                Intent.EXTRA_STREAM, getTheImageUri(this, img!!))
            intent.type = "img/*"

                intent.putExtra(
                    Intent.EXTRA_TEXT, ("Check out this Super Hero - ${fNameX[position]}"))
                intent.type = "text/plain"


            this.startActivity(Intent.createChooser(intent, "Send To"))
        }
    }

    private fun getTheImageUri(inContext: Context, inImage : Bitmap): Uri? {

        val noBytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, noBytes)

        val path = MediaStore.Images.Media.insertImage(inContext.contentResolver,inImage, "TL", null)
        return Uri.parse(path)
    }

    private fun getBitmapFromTheView(view : ImageView): Bitmap? {

        val bt = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bt)

        view.draw(canvas)
        return bt
    }
}


