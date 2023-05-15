package com.elshafee.androidclassmay.todolistapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androidclassmay.R
import com.elshafee.androidclassmay.databinding.ActivityTodoListApiBinding
import com.elshafee.androidclassmay.todolistapi.services.RetrofitInstanceTodoApi
import com.elshafee.androidclassmay.todolistapi.ui.utils.TodoListApiAdapter
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

class TodoListApiActivity : AppCompatActivity() {
    lateinit var binding:ActivityTodoListApiBinding
    lateinit var todolistadapter:TodoListApiAdapter
    private val TAG = "TodoListActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodoListApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        lifecycleScope.launchWhenCreated {
            binding.progressBarTodoListApi.isVisible=true
            val response = try {
                RetrofitInstanceTodoApi.api.getTodos()
            }catch (e:IOException){
                Log.d(TAG,"you don't have internet connection")
                binding.progressBarTodoListApi.isVisible=false
                return@launchWhenCreated
            }catch (e:HttpException){
                Log.d(TAG,"unexpected response")
                binding.progressBarTodoListApi.isVisible=false
                return@launchWhenCreated

            }

            if(response.isSuccessful && response.body() !=null){
                todolistadapter.todos = response.body()!!
            }else{
                Log.d(TAG,"Response is not Sucessful")
            }
            binding.progressBarTodoListApi.isVisible=false

        }


    }
    private fun setupRecyclerView()= binding.rvTodoListApi.apply {
todolistadapter = TodoListApiAdapter()
        adapter= todolistadapter
        layoutManager = LinearLayoutManager(this@TodoListApiActivity)
    }

}