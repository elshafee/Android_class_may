package com.elshafee.androidclassmay.firstorage.ui

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elshafee.androidclassmay.databinding.ActivityFireStorageAppBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

private const val REQUEST_CODE_PICKER = 0

class FireStorageApp : AppCompatActivity() {
    var currentImage: Uri? = null
    val imageRefrence = Firebase.storage.reference
    lateinit var binding: ActivityFireStorageAppBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFireStorageAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivstorage.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(it, REQUEST_CODE_PICKER)
            }
        }


        binding.btnupload.setOnClickListener {
            uploadImage("myImage")
        }

        binding.btndownload.setOnClickListener {
            downloadImage("myImage")
        }

        binding.btndelete.setOnClickListener {
            deletImage("myImage")
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_PICKER) {
            data?.data.let {
                currentImage = it
                binding.ivstorage.setImageURI(it)
            }
        }
    }

    private fun uploadImage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                currentImage?.let {
                    imageRefrence.child("images/$fileName").putFile(it).await()

                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@FireStorageApp, "Data Uploaded", Toast.LENGTH_SHORT)
                            .show()

                    }
                }

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStorageApp, e.message.toString(), Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }

    private fun downloadImage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val maxDownloadSize = 5L * 1024 * 1024
                val imageByte =
                    imageRefrence.child("images/$fileName").getBytes(maxDownloadSize).await()
                val imageBitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)



                withContext(Dispatchers.Main) {
                    binding.ivstorage.setImageBitmap(imageBitmap)
                    Toast.makeText(this@FireStorageApp, "Data Downloaded", Toast.LENGTH_SHORT)
                        .show()
                }

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStorageApp, e.message.toString(), Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }

    private fun deletImage(fileName: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {

                imageRefrence.child("images/$fileName").delete().await()


                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStorageApp, "Data Deleted", Toast.LENGTH_SHORT)
                        .show()
                }

            } catch (e: Exception) {

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FireStorageApp, e.message.toString(), Toast.LENGTH_SHORT)
                        .show()

                }
            }
        }
    }
}