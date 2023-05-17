package com.elshafee.androidclassmay.shoppingitemlist.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.elshafee.androidclassmay.shoppingitemlist.model.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase :RoomDatabase(){
    abstract fun getShoppingDao():ShoppingDao

    companion object {
        private val LOCK = Any()
        private var instance:ShoppingDatabase?=null
        operator fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?: createDatabase(context).also {
                instance = it
            }
        }

        private fun createDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            ShoppingDatabase::class.java,
            "ShoppingDB.db"
        ).build()
    }

}
