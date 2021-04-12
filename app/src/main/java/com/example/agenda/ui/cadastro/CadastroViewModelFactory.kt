package com.example.agenda.ui.cadastro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.agenda.database.repository.ClientesRepository

class CadastroViewModelFactory (private val repository: ClientesRepository) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CadastroViewModel(repository) as T
    }
}