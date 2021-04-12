package com.example.agenda.utils

import com.example.agenda.database.entity.ClienteEntity
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
    }
}