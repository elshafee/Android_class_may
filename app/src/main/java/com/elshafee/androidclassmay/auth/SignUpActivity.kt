package com.elshafee.androidclassmay.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elshafee.androidclassmay.HomeActivity
import com.elshafee.androidclassmay.MainActivity
import com.elshafee.androidclassmay.R
import com.elshafee.androidclassmay.auth.model.ProfileInformation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val profileInformationCollection = Firebase.firestore.collection("ProfileInformation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val name = findViewById<EditText>(R.id.etName)
        val username = findViewById<EditText>(R.id.et_username_r)
        val password = findViewById<EditText>(R.id.et_password_r)
        val confirmpassword = findViewById<EditText>(R.id.et_confirmpass)
        val login = findViewById<Button>(R.id.btn_signup)
        val signup = findViewById<TextView>(R.id.tv_login)
        auth = FirebaseAuth.getInstance()
        supportActionBar?.hide()
        login.setOnClickListener {
            val fullname = name.text.toString()
            val email = username.text.toString()
            val password = password.text.toString()
            val confirmpass = confirmpassword.text.toString()


            if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmpass.isEmpty()) {
                Toast.makeText(this, "Please enter your data correctly", Toast.LENGTH_LONG).show()
            } else {
                if (password == confirmpass && password.length >= 8) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                               val profiledata = ProfileInformation(fullname,email,password)
                                saveProfileInformation(profiledata)
                                val intent = Intent(this, HomeActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "Authentication Error", Toast.LENGTH_LONG)
                                    .show()

                            }
                        }

                } else {
                    Toast.makeText(
                        this,
                        "Password and confirmation must be same & at least 8 characters",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }

        signup.setOnClickListener {
            val intent = Intent(this, LoginScreen::class.java)
            startActivity(intent)
        }
    }

    private fun saveProfileInformation(profileInformation: ProfileInformation) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileInformationCollection.add(profileInformation)
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        this@SignUpActivity,
                        "Data Saved Successfully",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@SignUpActivity,
                    e.message.toString(),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}