package com.example.itspowtime

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.room.Room
import com.example.itspowtime.Fav.UserDatabase
import com.example.itspowtime.databinding.ActivityLoginPageBinding
import com.example.itspowtime.databinding.ActivityMainBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginPage : AppCompatActivity() {

    lateinit var binding: ActivityLoginPageBinding

    override fun onBackPressed() {
        super.onBackPressed()
        System.exit(0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.load.isVisible = false

        binding.l6.setOnClickListener() {
            val intent = Intent(this, CreateId::class.java)
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(this)
            startActivity(intent, compat.toBundle())
            finish()
        }

        binding.ref.setOnClickListener(){
            val myRoomDatabase = Room.databaseBuilder(this, UserDatabase::class.java,"favS").allowMainThreadQueries().build()
            myRoomDatabase.userDao().clear()
        }

        binding.l4.setOnClickListener() {
            when {
                //checks to see if the email field is empty. If empty gives a toast
                TextUtils.isEmpty(binding.l2.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Kindly enter your email", Toast.LENGTH_SHORT).show()
                }
                //checks to see if the email field is empty. If empty gives a toast
                TextUtils.isEmpty(binding.l3.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(this, "Kindly enter your password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    binding.load.isVisible = true
                    val userEmail = binding.l2.text.toString().trim { it <= ' ' }
                    val userPassword = binding.l3.text.toString().trim { it <= ' ' }

                    //create an account for these credentials now
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(userEmail, userPassword)
                        .addOnCompleteListener(
                            OnCompleteListener<AuthResult> { task ->

                                //if the registration is successful
                                if (task.isSuccessful) {

                                    //creates acc for firebase user
                                    //gives toast if successful
                                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT)
                                        .show()

                                    val intent = Intent(this, HomePage::class.java)
                                    intent.flags =
                                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("pawsome_email_id", userEmail)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    //if registration is not successful
                                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                                    binding.load.isVisible = false

                                }
                            }
                        )
                }
            }
        }
    }
}