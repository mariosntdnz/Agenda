package com.example.agenda.utils

import com.example.agenda.database.entity.ClienteEntity
import com.example.agenda.domain.Cliente
import com.example.agenda.ui.cadastro.RegisterClientModel

class Parse {
    companion object{
        fun clienteRegisterToEntity(clientModel: RegisterClientModel) = clientModel.let {
            ClienteEntity(
                null,
                it.nome.get(),
                it.cpf.get(),
                it.dataCadastro.get(),
                it.dataNascimento.get(),
                it.UF,
            )
        }
        fun parseClienteEntityToClient(clienteEntity: ClienteEntity) = clienteEntity.let {
            Cliente(
                it.id,
                it.nome,
                it.cpf,
                it.dataCadastro,
                it.dataNascimento,
                it.UF,
                null
            )
        }
    }
}