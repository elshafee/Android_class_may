package com.elshafee.androidclassmay.todolistapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elshafee.androidclassmay.databinding.ItemTodoBinding

class TodoAdapter(var todos:List<Todos>):RecyclerView.Adapter<TodoAdapter.TodoViewHolder> (){

    inner class TodoViewHolder(val binding:ItemTodoBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            tvtodotitle.text = todos[position].title
            cbTodoDone.isChecked = todos[position].isCheck
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}