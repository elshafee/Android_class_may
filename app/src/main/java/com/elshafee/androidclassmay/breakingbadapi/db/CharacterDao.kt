package com.elshafee.androidclassmay.breakingbadapi.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elshafee.androidclassmay.breakingbadapi.model.BreakingBadCharacter

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun findallcharacters():LiveData<List<BreakingBadCharacter>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCharacters(character: List<BreakingBadCharacter>)
}