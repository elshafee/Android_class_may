package com.elshafee.androidclassmay.fragments.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elshafee.androidclassmay.databinding.ViewpagerItemBinding

class ViewPagerAdapter(var images: List<Int>) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ViewpagerItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        return ViewPagerViewHolder(
            ViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        val currentImage = images[position]
        holder.binding.ivEventImage.setImageResource(currentImage)
    }

    override fun getItemCount(): Int {
        return images.size
    }
}