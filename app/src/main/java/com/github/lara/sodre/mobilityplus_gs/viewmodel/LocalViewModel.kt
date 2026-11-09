package com.github.lara.sodre.mobilityplus_gs.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.lara.sodre.mobilityplus_gs.data.Local
import com.github.lara.sodre.mobilityplus_gs.data.LocalRepository
import com.github.lara.sodre.mobilityplus_gs.data.PreferenciasAcessibilidade
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch


class LocalViewModel(
    private val repository: LocalRepository
) : ViewModel() {

    // MENSAGENS PARA O USUÁRIO (exibidas em Snackbar)

    private val _mensagens = MutableSharedFlow<String>(extraBufferCapacity = 8)
    val mensagens: SharedFlow<String> = _mensagens.asSharedFlow()

    // ESTABELECIMENTOS

    val locais = repository.listar().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun cadastrar(local: Local) {
        viewModelScope.launch {
            repository.inserir(local)
            _mensagens.tryEmit("Estabelecimento cadastrado com sucesso!")
        }
    }

    fun editar(local: Local) {
        viewModelScope.launch {
            repository.atualizar(local)
            _mensagens.tryEmit("Estabelecimento atualizado!")
        }
    }

    fun alternarFavorito(local: Local) {
        viewModelScope.launch {
            repository.atualizar(local.copy(favorito = !local.favorito))
            _mensagens.tryEmit(
                if (local.favorito) "Removido dos favoritos."
                else "Adicionado aos favoritos!"
            )
        }
    }

    fun excluir(local: Local) {
        viewModelScope.launch {
            repository.excluir(local)
            _mensagens.tryEmit("Estabelecimento excluído.")
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

    // PREFERÊNCIAS DE ACESSIBILIDADE

    val preferencias = repository.observarPreferencias()
        .map { preferenciasSalvas ->
            preferenciasSalvas ?: PreferenciasAcessibilidade()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = PreferenciasAcessibilidade()
        )

    fun salvarPreferencias(
        preferencias: PreferenciasAcessibilidade,
        resultado: (Boolean) -> Unit = {}
    ) {
        viewModelScope.launch {

            try {

                repository.salvarPreferencias(preferencias)

                resultado(true)

            } catch (erro: Exception) {

                resultado(false)
            }
        }
    }
}