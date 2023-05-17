package com.elshafee.androidclassmay.breakingbadapi.services

import com.elshafee.androidclassmay.breakingbadapi.model.BreakingBadCharacter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://63c4417ba908563575346178.mockapi.io/api/v1/"
interface BreakingBadServices {
    @GET("characters")
    suspend fun getCharacters():List<BreakingBadCharacter>
}

object BreakingBadNetwork{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val serviceApi = retrofit.create(BreakingBadServices::class.java)
}