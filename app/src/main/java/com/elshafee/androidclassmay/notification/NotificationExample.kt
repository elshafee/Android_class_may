package com.elshafee.androidclassmay.notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.elshafee.androidclassmay.MainActivity
import com.elshafee.androidclassmay.R
import com.elshafee.androidclassmay.databinding.ActivityNotificationExampleBinding
import java.util.jar.Manifest

class NotificationExample : AppCompatActivity() {
    val CHANNEL_ID = "AndroidClassMayID"
    val CHANNEL_NAME = "AndroidClassMayNAME"
    val NOTIFICATION_ID = 0
    lateinit var binding: ActivityNotificationExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()

        val intent = Intent(this, NotificationExample::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val eventTicketNotification = NotificationCompat.Builder(
            this, CHANNEL_ID
        ).setContentTitle("Your Event Ticket")
            .setContentText("You are invited to attend our event")
            .setSmallIcon(R.drawable.android)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()
        val eventManager = NotificationManagerCompat.from(this)

        binding.btnShowNotification.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
            )!= PackageManager.PERMISSION_GRANTED){

            }
            eventManager.notify(NOTIFICATION_ID,eventTicketNotification)
        }
    }

    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.RED
                enableLights(true)
            }

            val manager =  getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}