package com.elshafee.androidclassmay.shoppingitemlist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elshafee.androidclassmay.shoppingitemlist.repositry.ShoppingRepositry

class ShoppingViewModelFactory(private val repositry: ShoppingRepositry):ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repositry) as T
    }
}