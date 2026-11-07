package com.github.lara.sodre.mobilityplus_gs.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "locais")
data class Local(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nome: String,
    val endereco: String,
    val descricao: String,

    val rampa: Boolean,
    val pisoTatil: Boolean,
    val sinalizacaoSonora: Boolean,

    val sinalizacaoVisual: Boolean = false,
    val favorito: Boolean = false
)