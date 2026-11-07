package com.github.lara.sodre.mobilityplus_gs.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lara.sodre.mobilityplus_gs.data.Local
import com.github.lara.sodre.mobilityplus_gs.data.LocalRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LocalViewModel(
    private val repository: LocalRepository
) : ViewModel() {

    val locais = repository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun cadastrar(local: Local) {
        viewModelScope.launch {
            repository.inserir(local)
        }
    }

    fun editar(local: Local) {
        viewModelScope.launch {
            repository.atualizar(local)
        }
    }

    fun excluir(local: Local) {
        viewModelScope.launch {
            repository.excluir(local)
        }
    }

    fun buscar(
        id: Int,
        resultado: (Local?) -> Unit
    ) {
        viewModelScope.launch {
            resultado(repository.buscar(id))
        }
    }
}