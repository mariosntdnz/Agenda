package com.example.agenda.database.repository

import com.example.agenda.core.MyApplication
import com.example.agenda.database.entity.TelefoneEntity
import com.example.agenda.ui.cadastro.RegisterClientModel
import com.example.agenda.utils.Parse

class ClientesRepository {

    private val bd = MyApplication.database?.clientesDao()!!

    fun insertCliente(clienteRegisterClientModel: RegisterClientModel){
        val idCliente = bd.insertCliente(Parse.clienteRegisterToEntity(clienteRegisterClientModel))
        bd.insertTelefones(clienteRegisterClientModel.telefones.map {
                TelefoneEntity(null,idCliente,it)
            }
        )
    }
}