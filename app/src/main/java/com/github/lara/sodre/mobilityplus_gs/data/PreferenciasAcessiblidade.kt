package com.github.lara.sodre.mobilityplus_gs.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "preferencias_acessibilidade")
data class PreferenciasAcessibilidade(

    @PrimaryKey
    val id: Int = 1,

    val fisica: Boolean = false,
    val visual: Boolean = false,
    val auditiva: Boolean = false,
    val intelectual: Boolean = false,

    val cadeiraRodas: Boolean = false,
    val bengala: Boolean = false,
    val mobilidadeReduzida: Boolean = false
)
