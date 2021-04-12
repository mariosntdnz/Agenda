package com.example.agenda.ui.cadastro


import androidx.lifecycle.ViewModel
import com.example.agenda.database.repository.ClientesRepository

class CadastroViewModel(repository: ClientesRepository) : ViewModel() {
    val registerClientModel = RegisterClientModel()
}