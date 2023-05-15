package com.elshafee.androidclassmay.breakingbadapi.ui

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elshafee.androidclassmay.breakingbadapi.repository.CharacterRepository

class CharacterListViewModelFactory (private val characterRepository: CharacterRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharcterListViewModel::class.java)){
            return CharcterListViewModel(characterRepository) as T
        }
        throw java.lang.IllegalAccessException("UnKnown ViewModel Class")
    }
}