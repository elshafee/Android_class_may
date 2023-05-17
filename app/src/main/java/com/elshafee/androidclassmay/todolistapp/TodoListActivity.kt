package com.elshafee.androidclassmay.todolistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.elshafee.androidclassmay.R
import com.elshafee.androidclassmay.databinding.ActivityTodoListBinding

class TodoListActivity : Fragment() {
    lateinit var binding:ActivityTodoListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityTodoListBinding.inflate(layoutInflater)
        val view = binding.root
        val todolistItem = mutableListOf(
            Todos("doiing android session",true),
            Todos("doiing android session",false),
            Todos("doiing android session",false),
            Todos("doiing android session",false),
            Todos("doiing android session",false),
            Todos("doiing android session",false),
            Todos("doiing android session",false),
            Todos("doiing android session",false),
            Todos("doiing android session",false),
            Todos("doiing android session",false),
        )
        val adapter = TodoAdapter(todolistItem)
        binding.rvTodos.adapter= adapter
        binding.rvTodos.layoutManager = LinearLayoutManager(activity)

        binding.btnTodo.setOnClickListener {
            val newtitle= binding.etAddTodo.text.toString()
            val newtodo = Todos(newtitle,false)

            todolistItem.add(newtodo)
            adapter.notifyDataSetChanged()
            binding.etAddTodo.text.clear()
        }
        return view
    }
}