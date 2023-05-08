package com.elshafee.androidclassmay.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.elshafee.androidclassmay.MainActivity
import com.elshafee.androidclassmay.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        val username = findViewById<EditText>(R.id.et_username_r)
        val password = findViewById<EditText>(R.id.et_password_r)
        val confirmpassword = findViewById<EditText>(R.id.et_confirmpass)
        val login = findViewById<Button>(R.id.btn_signup)
        val signup = findViewById<TextView>(R.id.tv_login)
        supportActionBar?.hide()
    login.setOnClickListener {
        val email = username.text.toString()
        val password = password.text.toString()
        val confirmpass = confirmpassword.text.toString()

        if (email.isEmpty() || password.isEmpty() || confirmpass.isEmpty()){
            Toast.makeText(this,"Please enter your data correctly", Toast.LENGTH_LONG).show()
        }else{
            if (password == confirmpass && password.length >=8){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this,"Password and confirmation must be same & at least 8 characters", Toast.LENGTH_LONG).show()

            }
        }
    }

        signup.setOnClickListener{
            val intent = Intent(this,LoginScreen::class.java)
            startActivity(intent)
        }
    }
}