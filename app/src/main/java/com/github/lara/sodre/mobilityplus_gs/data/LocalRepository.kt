package com.github.lara.sodre.mobilityplus_gs.data

import kotlinx.coroutines.flow.Flow

class LocalRepository(
    private val dao: LocalDao
) {

    fun listar(): Flow<List<Local>> {
        return dao.listar()
    }

    suspend fun buscar(id: Int): Local? {
        return dao.buscar(id)
    }

    suspend fun inserir(local: Local) {
        dao.inserir(local)
    }

    suspend fun atualizar(local: Local) {
        dao.atualizar(local)
    }

    suspend fun excluir(local: Local) {
        dao.excluir(local)
    }
}