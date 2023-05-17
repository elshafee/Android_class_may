package com.elshafee.androidclassmay.firstorage.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.elshafee.androidclassmay.databinding.ItemStorageBinding

class StorageAdapter(var urls: List<String>) :
    RecyclerView.Adapter<StorageAdapter.StorageAdapterViewHolder>() {


    inner class StorageAdapterViewHolder(val binding: ItemStorageBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageAdapterViewHolder {
        return StorageAdapterViewHolder(
            ItemStorageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StorageAdapterViewHolder, position: Int) {
        val url = urls[position]
        Glide.with(holder.itemView).load(url).centerCrop().into(holder.binding.ivStorageitem)
    }

    override fun getItemCount(): Int {
        return urls.size
    }

}