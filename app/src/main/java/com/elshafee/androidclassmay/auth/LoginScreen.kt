package com.elshafee.androidclassmay.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.elshafee.androidclassmay.MainActivity
import com.elshafee.androidclassmay.R

class LoginScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        supportActionBar?.hide()
        val username = findViewById<EditText>(R.id.et_username)
        val password = findViewById<EditText>(R.id.et_password)
        val login = findViewById<Button>(R.id.btn_login)
        val signup = findViewById<TextView>(R.id.tv_register)


        login.setOnClickListener {
            val email:String = username.text.toString()
            val password:String = password.text.toString()
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"Please Enter your Email and password", Toast.LENGTH_LONG).show()

            }else{
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        signup.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}