package com.example.agenda.database.repository

import com.example.agenda.core.MyApplication
import com.example.agenda.database.entity.TelefoneEntity
import com.example.agenda.domain.Cliente
import com.example.agenda.ui.cadastro.RegisterClientModel
import com.example.agenda.utils.Parse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class ClientesRepository {

    private val bd = MyApplication.database?.clientesDao()!!

    fun insertCliente(clienteRegisterClientModel: RegisterClientModel){
        val idCliente = bd.insertCliente(Parse.clienteRegisterToEntity(clienteRegisterClientModel))
        bd.insertTelefones(clienteRegisterClientModel.telefones.map {
                TelefoneEntity(null,idCliente,it)
            }
        )
    }

    fun getAllClientes() : Flow<List<Cliente>> {
        return bd.getClienteWithTelefone().transform {it ->
            this.emit(
                it.map {
                    val cliente = Parse.parseClienteEntityToClient(it.clienteEntity)
                    val telefones = it.telefoneList.map {
                        it.telefone
                    }
                    cliente.telefones = telefones
                    cliente
                }
            )
        }
    }
}