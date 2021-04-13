package com.example.agenda.ui.clientes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.agenda.database.repository.ClientesRepository
import com.example.agenda.domain.Cliente
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class ClientesViewModel(val repository: ClientesRepository) : ViewModel() {

    private val _contatoClicado = MutableLiveData<Cliente?>()
    val contatoClicado : MutableLiveData<Cliente?>
        get() = _contatoClicado

    var clientes : Flow<List<Cliente>>

    init {
        clientes = repository.getAllClientes()
    }

    fun deleteCliente(cliente: Cliente){
        repository.deleteCliente(cliente)
    }

    fun setContatoClicado(contato: Cliente){
        _contatoClicado.value = contato
    }

    fun navigationTelaDetalhes() {
        _contatoClicado.value = null
    }
}