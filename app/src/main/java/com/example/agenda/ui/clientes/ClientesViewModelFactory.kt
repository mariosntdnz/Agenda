package com.example.agenda.ui.clientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agenda.database.repository.ClientesRepository

class ClientesViewModelFactory (private val repository: ClientesRepository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ClientesViewModel(repository) as T
    }
}