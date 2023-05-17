package com.elshafee.androidclassmay.firebasenotification.ui

import android.content.Context
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.elshafee.androidclassmay.R
import com.elshafee.androidclassmay.databinding.ActivityCloudMessaginfBinding
import com.elshafee.androidclassmay.firebasenotification.model.NotificationData
import com.elshafee.androidclassmay.firebasenotification.model.PushNotification
import com.elshafee.androidclassmay.firebasenotification.services.FirebaseService
import com.elshafee.androidclassmay.firebasenotification.services.RetrofitInstance
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
const val TOPIC = "/topics/myTopic"
class CloudMessaging : AppCompatActivity() {
    val TAG = "CloudMessagingApp"
    private lateinit var binding:ActivityCloudMessaginfBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCloudMessaginfBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseService.sharedPrefrence = getSharedPreferences("sharedPrefFile",Context.MODE_PRIVATE)
        val messaging = FirebaseMessaging.getInstance()
        messaging.token.addOnSuccessListener {
            FirebaseService.token = it
            binding.etnotificationToken.setText(it)
        }
        messaging.subscribeToTopic(TOPIC)
        binding.btnSendNotification.setOnClickListener {
            val notificationTitle = binding.etnotificationTitle.text.toString()
            val notificationMessage = binding.etnotificationMessage.text.toString()
            val notificationToken = binding.etnotificationToken.text.toString()

            if (notificationTitle.isNotEmpty() && notificationMessage.isNotEmpty() && notificationToken.isNotEmpty()){
                PushNotification(NotificationData(notificationTitle,notificationMessage),notificationToken).also {
                    sendNotification(it)
                }
            }
        }

    }

    private fun sendNotification(notification:PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotificattion(notification)
            if (response.isSuccessful) {
                Log.d(TAG,"sent Successfully")
            }else{
                Log.d(TAG,response.errorBody().toString())

            }
        }catch (e:Exception){
            Log.d(TAG,e.message.toString())
        }
    }
}