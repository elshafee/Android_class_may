package com.elshafee.androidclassmay.breakingbadapi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.elshafee.androidclassmay.breakingbadapi.model.BreakingBadCharacter


@Database(
    entities = [BreakingBadCharacter::class],
    version = 1
)
@TypeConverters(AppTypeConverters::class)
abstract class CharacterDatabase :RoomDatabase(){
    // dao access function
abstract fun ceateCharacterDao():CharacterDao

    companion object {
        private var INSTANCE:CharacterDatabase?=null

        fun createDatabse(context: Context):CharacterDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "breakingbd.db"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }

}