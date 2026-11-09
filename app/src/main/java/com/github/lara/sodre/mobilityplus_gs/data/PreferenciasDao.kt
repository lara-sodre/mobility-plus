package com.github.lara.sodre.mobilityplus_gs.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PreferenciasDao {

    @Query(
        "SELECT * FROM preferencias_acessibilidade WHERE id = 1"
    )
    fun observar(): Flow<PreferenciasAcessibilidade?>

    @Query(
        "SELECT * FROM preferencias_acessibilidade WHERE id = 1"
    )
    suspend fun buscar(): PreferenciasAcessibilidade?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun salvar(
        preferencias: PreferenciasAcessibilidade
    )
}
