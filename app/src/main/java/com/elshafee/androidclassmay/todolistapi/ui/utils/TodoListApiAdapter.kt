package com.elshafee.androidclassmay.todolistapi.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elshafee.androidclassmay.databinding.ItemTodolistBinding
import com.elshafee.androidclassmay.todolistapi.model.TodoListApiData

class TodoListApiAdapter : RecyclerView.Adapter<TodoListApiAdapter.TodoListApiViewHolder>() {

    private val diffCallBack = object: DiffUtil.ItemCallback<TodoListApiData>(){
        override fun areItemsTheSame(oldItem: TodoListApiData, newItem: TodoListApiData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TodoListApiData,
            newItem: TodoListApiData
        ): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this,diffCallBack)
    var todos: List<TodoListApiData>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    inner class TodoListApiViewHolder(val binding: ItemTodolistBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListApiViewHolder {
        return TodoListApiViewHolder(
            ItemTodolistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: TodoListApiViewHolder, position: Int) {

        holder.binding.apply {
            val singleTodo = todos[position]
            tvTitleTodo.text = singleTodo.title
            cbTodoApi.isChecked = singleTodo.completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}