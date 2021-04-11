package com.example.agenda.database.entity

import androidx.room.Embedded
import androidx.room.Relation

class ClienteWithTelefone (
    @Embedded val clienteEntity: ClienteEntity,
    @Relation(
        parentColumn = "cliente_id",
        entityColumn = "telefone_id"
    )
    val telefoneList : List<TelefoneEntity>
)
