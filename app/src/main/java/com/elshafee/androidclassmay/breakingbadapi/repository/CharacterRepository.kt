package com.elshafee.androidclassmay.breakingbadapi.repository

import androidx.lifecycle.LiveData
import com.elshafee.androidclassmay.breakingbadapi.db.CharacterDao
import com.elshafee.androidclassmay.breakingbadapi.model.BreakingBadCharacter
import com.elshafee.androidclassmay.breakingbadapi.services.BreakingBadNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val characterDao:CharacterDao) {

    var characters:LiveData<List<BreakingBadCharacter>> = characterDao.findallcharacters()


    suspend fun refreshCharacters(){
        withContext(Dispatchers.IO){
            val characters = BreakingBadNetwork.serviceApi.getCharacters()
            characterDao.insertAllCharacters(characters)
        }
    }
}