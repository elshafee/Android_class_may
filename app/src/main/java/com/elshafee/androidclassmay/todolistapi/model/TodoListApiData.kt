package com.elshafee.androidclassmay.todolistapi.model

data class TodoListApiData (
    val completed:Boolean,
    val title:String,
    val id:Int,
    val userId:Int,
        ){
}