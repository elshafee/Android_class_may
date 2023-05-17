package com.elshafee.androidclassmay.todolistapi.services

import com.elshafee.androidclassmay.todolistapi.model.TodoListApiData
import retrofit2.Response
import retrofit2.http.GET

interface TodoListApi {

    @GET("/todos")
    suspend fun getTodos():Response<List<TodoListApiData>>
}