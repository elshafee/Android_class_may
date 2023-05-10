package com.elshafee.androidclassmay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.btn_hello)
        val etname = findViewById<EditText>(R.id.et_name)
        val hellotv = findViewById<TextView>(R.id.text_hello)
val cbagree = findViewById<CheckBox>(R.id.cb_agree)
        button.setOnClickListener {
          val name:String =   etname.text.toString()
            hellotv.text = name

            val agree = cbagree.isChecked

            Toast.makeText(this,"$name is agree? $agree",Toast.LENGTH_SHORT).show()
        }
    }
}