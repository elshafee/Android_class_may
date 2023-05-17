package com.elshafee.androidclassmay.breakingbadapi.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elshafee.androidclassmay.breakingbadapi.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharcterListViewModel(private val characterRepository: CharacterRepository): ViewModel(){

    val characterList = characterRepository.characters

    fun refreshDataFromReposotry(){
        viewModelScope.launch {
            characterRepository.refreshCharacters()
        }
    }
}