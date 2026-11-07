package com.github.lara.sodre.mobilityplus_gs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.lara.sodre.mobilityplus_gs.data.LocalRepository

class LocalViewModelFactory(
    private val repository: LocalRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(LocalViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return LocalViewModel(repository) as T
        }

        throw IllegalArgumentException("ViewModel desconhecido")
    }
}