package com.elshafee.androidclassmay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.elshafee.androidclassmay.auth.LoginScreen
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()
        var currentUser = auth.currentUser
        Handler().postDelayed({
            if (currentUser !=null){
                val intent = Intent(this,HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this,LoginScreen::class.java)
                startActivity(intent)
                finish()
            }


        },3000)
    }
}