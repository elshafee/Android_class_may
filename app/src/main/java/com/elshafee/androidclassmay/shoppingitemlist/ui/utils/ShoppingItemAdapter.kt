package com.elshafee.androidclassmay.shoppingitemlist.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elshafee.androidclassmay.databinding.ItemShoppingBinding
import com.elshafee.androidclassmay.shoppingitemlist.model.ShoppingItem
import com.elshafee.androidclassmay.shoppingitemlist.ui.ShoppingViewModel

class ShoppingItemAdapter(var items: List<ShoppingItem>, private val viewModel: ShoppingViewModel) :
    RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>() {

    inner class ShoppingViewHolder(val binding: ItemShoppingBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        return ShoppingViewHolder(
            ItemShoppingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        var currentShoppigItem = items[position]
        holder.binding.tvitemName.text= currentShoppigItem.name
        holder.binding.tvitemAmount.text = "${ currentShoppigItem.amount}"

        holder.binding.ivShoppingItemDelete.setOnClickListener {
            viewModel.delete(currentShoppigItem)
        }

        holder.binding.ivShoppingItemAdd.setOnClickListener {
            currentShoppigItem.amount++
            viewModel.upsert(currentShoppigItem)
        }
        holder.binding.ivShoppingItemMinus.setOnClickListener {
            if (currentShoppigItem.amount > 1){
                currentShoppigItem.amount--
                viewModel.upsert(currentShoppigItem)
            }else if (currentShoppigItem.amount <=1){
                viewModel.delete(currentShoppigItem)
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}