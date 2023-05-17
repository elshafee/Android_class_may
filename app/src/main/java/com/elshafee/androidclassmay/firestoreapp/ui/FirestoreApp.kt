package com.elshafee.androidclassmay.firestoreapp.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elshafee.androidclassmay.auth.model.ProfileInformation
import com.elshafee.androidclassmay.databinding.ActivityFirestoreAppBinding
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class FirestoreApp : AppCompatActivity() {
    lateinit var binding: ActivityFirestoreAppBinding
    private val profileDetailsColl = Firebase.firestore.collection("ProfileInformation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirestoreAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnsave.setOnClickListener {

            val oldProfileData = getOldProfiledata()
            saveProfiledata(oldProfileData)


        }
        binding.btnretrieve.setOnClickListener {
            RetrieveData()
        }
        binding.btnupdate.setOnClickListener {
            val oldProfileData = getOldProfiledata()
            val newProfileData = getNewProfileData()
            updateProfileDetails(oldProfileData,newProfileData)


        }
        binding.btndelete.setOnClickListener {
            val oldProfileData = getOldProfiledata()

            deleteProfileDetails(oldProfileData)
        }
    }


    fun getOldProfiledata(): ProfileInformation {

        val oldFullName = binding.oldfullname.text.toString()
        val oldemail = binding.oldEmail.text.toString()
        val oldpassword = binding.oldPassword.text.toString()
        return ProfileInformation(oldFullName, oldemail, oldpassword)
    }

    fun getNewProfileData(): Map<String, Any> {
        val newFullName = binding.newfullName.text.toString()
        val newemail = binding.newEmail.text.toString()
        val newpassword = binding.newpassword.text.toString()
        val map = mutableMapOf<String, Any>()
        if (newFullName.isNotEmpty()) {
            map["fullName"] = newFullName
        }
        if (newemail.isNotEmpty()) {
            map["emailAdress"] = newemail
        }
        if (newpassword.isNotEmpty()) {
            map["password"] = newpassword
        }

        return map
    }

    fun saveProfiledata(profiledata: ProfileInformation) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileDetailsColl.add(profiledata)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirestoreApp, "Data Updated", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirestoreApp, e.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }
    }


    private fun RetrieveData() {

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val resualt = profileDetailsColl.get().await()
                val stringbuilder = StringBuilder()
                for (document in resualt.documents) {
                    val profileInformation = document.toObject<ProfileInformation>()
                    stringbuilder.append("$profileInformation\n")
                }
                withContext(Dispatchers.Main) {
                    binding.tvshowProfiledata.text = stringbuilder.toString()
                    Toast.makeText(this@FirestoreApp, "Data Updated", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirestoreApp, e.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }
    }

    private fun deleteProfileDetails(profiledata: ProfileInformation) {
        CoroutineScope(Dispatchers.IO).launch {
            val profileData = profileDetailsColl.whereEqualTo("fullName", profiledata.fullName)
                .whereEqualTo("emailAdress", profiledata.emailAdress)
                .whereEqualTo("password", profiledata.Password).get().await()
            try {
                if (profileData.documents.isNotEmpty()) {
                    for (document in profileData) {
                        profileDetailsColl.document(document.id).delete().await()

                    }
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirestoreApp, "Data Deleted", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirestoreApp, e.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }
    }

    private fun updateProfileDetails(oldprofile: ProfileInformation, newdata: Map<String, Any>) {
        CoroutineScope(Dispatchers.IO).launch {
            val profileData = profileDetailsColl.whereEqualTo("fullName", oldprofile.fullName)
                .whereEqualTo("emailAdress", oldprofile.emailAdress)
                .whereEqualTo("password", oldprofile.Password).get().await()
            try {
                if (profileData.documents.isNotEmpty()) {
                    for (document in profileData) {
                        profileDetailsColl.document(document.id).set(
                            newdata, SetOptions.merge()
                        ).await()

                    }
                }

                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirestoreApp, "Data Updated", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@FirestoreApp, e.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }
    }
}