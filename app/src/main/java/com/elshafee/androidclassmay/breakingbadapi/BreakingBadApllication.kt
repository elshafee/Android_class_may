package com.elshafee.androidclassmay.breakingbadapi

import android.app.Application
import com.elshafee.androidclassmay.breakingbadapi.db.CharacterDatabase
import com.elshafee.androidclassmay.breakingbadapi.repository.CharacterRepository

class BreakingBadApllication:Application() {

    val database by lazy {
        CharacterDatabase.createDatabse(this)
    }

    val characterRepository by lazy {
        CharacterRepository(database.ceateCharacterDao())
    }
}