package com.example.a21twelvepractical.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a21twelvepractical.activity.allProduct.AllProductShowViewModel

class ViewModelFactory(): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AllProductShowViewModel::class.java) -> AllProductShowViewModel() as T
            else ->  throw IllegalArgumentException("ViewModelClass Not found")
        }
    }
}