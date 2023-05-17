package com.elshafee.androidclassmay.shoppingitemlist.ui

import androidx.lifecycle.ViewModel
import com.elshafee.androidclassmay.shoppingitemlist.model.ShoppingItem
import com.elshafee.androidclassmay.shoppingitemlist.repositry.ShoppingRepositry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repositry: ShoppingRepositry):ViewModel(){

    fun upsert(item:ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repositry.upsert(item)
    }
    fun delete(item:ShoppingItem) = CoroutineScope(Dispatchers.IO).launch {
        repositry.delete(item)
    }
    fun getAllShoppingItem() = repositry.getAllShoppingItems()
}