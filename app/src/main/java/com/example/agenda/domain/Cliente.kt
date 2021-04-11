package com.example.agenda.domain

class Cliente(
    val id : Int,
    val nome: String,
    val cpf: String,
    val dataCadastro : String,
    val dataNascimento : String,
    val UF : String,
    val telefones : List<String>
)