package com.example.agenda.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "telefone")
data class TelefoneEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val clienteId: Long,
    val telefone: String
)