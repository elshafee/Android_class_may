package com.elshafee.androidclassmay.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.elshafee.androidclassmay.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class CoroutineExample : AppCompatActivity() {
    val TAG = "CoroutineExample"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_example)


        GlobalScope.async(Dispatchers.IO){
            val time = measureTimeMillis {
                val answer1 = async { networkCall1() }
                val answer2 = async { networkCall2() }
                Log.d(TAG,"Answer 1 is ${answer1.await()}")
                Log.d(TAG,"Answer 2 is ${answer2.await()}")


//                withContext(Dispatchers.Main){
//                    Toast.makeText(this@CoroutineExample,"$answer1",Toast.LENGTH_SHORT).show()
//                }
            }
            Log.d(TAG,"Requested time is $time ms")

            withContext(Dispatchers.Main){
                Toast.makeText(this@CoroutineExample,"$time",Toast.LENGTH_SHORT).show()

            }



        }
    }

    suspend fun networkCall1():String{
        delay(3000L)
        return "Answer 1"
    }
    suspend fun networkCall2():String{
        delay(3000L)
        return "Answer 2"
    }

}