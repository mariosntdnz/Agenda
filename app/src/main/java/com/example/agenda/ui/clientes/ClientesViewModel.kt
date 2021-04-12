package com.example.agenda.ui.clientes

import androidx.lifecycle.ViewModel
import com.example.agenda.database.repository.ClientesRepository
import com.example.agenda.domain.Cliente
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ClientesViewModel(val repository: ClientesRepository) : ViewModel() {

    //private var _clientes : Flow <List<Cliente>>
    var clientes : Flow<List<Cliente>>
        //get() = _clientes


    init {
        clientes = repository.getAllClientes()
    }
}