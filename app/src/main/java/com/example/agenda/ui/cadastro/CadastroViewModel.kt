package com.example.agenda.ui.cadastro


import androidx.lifecycle.ViewModel
import com.example.agenda.database.repository.ClientesRepository

class CadastroViewModel(val repository: ClientesRepository) : ViewModel() {
    val registerClientModel = RegisterClientModel()

    fun insertClienteBD(){
        repository.insertCliente(registerClientModel)
    }

    fun insertTelefone(telefone : String,index : Int){
        if(index  == registerClientModel.telefones.size){
            registerClientModel.telefones.add(index,telefone)
        }
        else{
            registerClientModel.telefones[index] = telefone
        }
    }
}