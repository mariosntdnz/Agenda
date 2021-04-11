package com.example.agenda.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cliente")
data class ClienteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cliente_id")
    val id : Int,
    val nome: String,
    val cpf: String,
    val dataCadastro : String,
    val dataNascimento : String,
    val UF : String,
)