package com.elshafee.androidclassmay.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.elshafee.androidclassmay.HomeActivity
import com.elshafee.androidclassmay.MainActivity
import com.elshafee.androidclassmay.R
import com.google.firebase.auth.FirebaseAuth

class LoginScreen : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
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
             auth.signInWithEmailAndPassword(email, password).addOnCompleteListener{task->
                 if(task.isSuccessful){
                     val intent = Intent(this,HomeActivity::class.java)
                     startActivity(intent)
                     finish()
                 }else{
                     Toast.makeText(this,"Wrong Email or Password", Toast.LENGTH_LONG).show()

                 }
             }

            }
        }

        signup.setOnClickListener{
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}