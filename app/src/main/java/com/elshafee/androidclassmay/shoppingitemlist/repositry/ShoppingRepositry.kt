package com.elshafee.androidclassmay.shoppingitemlist.repositry

import com.elshafee.androidclassmay.shoppingitemlist.db.ShoppingDatabase
import com.elshafee.androidclassmay.shoppingitemlist.model.ShoppingItem

class ShoppingRepositry(private val db:ShoppingDatabase) {
    fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    fun delete(item:ShoppingItem) = db.getShoppingDao().delete(item)

    fun getAllShoppingItems()= db.getShoppingDao().getAllShoppingItem()
}