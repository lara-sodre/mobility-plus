package com.github.lara.sodre.mobilityplus_gs.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {

    @Query("SELECT * FROM locais ORDER BY nome ASC")
    fun listar(): Flow<List<Local>>

    @Query("SELECT * FROM locais WHERE id = :id")
    suspend fun buscar(id: Int): Local?

    @Insert
    suspend fun inserir(local: Local)

    @Update
    suspend fun atualizar(local: Local)

    @Delete
    suspend fun excluir(local: Local)
}