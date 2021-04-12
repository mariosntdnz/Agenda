package com.example.agenda.ui.cadastro

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import com.example.agenda.utils.Constantes.END_INDEX_ANO
import com.example.agenda.utils.Constantes.END_INDEX_DIA
import com.example.agenda.utils.Constantes.END_INDEX_MES
import com.example.agenda.utils.Constantes.IDADE_CADASTRO_MG
import com.example.agenda.utils.Constantes.SIZE_CPF
import com.example.agenda.utils.Constantes.SIZE_DATA_CADASTRO_DEFAULT
import com.example.agenda.utils.Constantes.START_INDEX_ANO
import com.example.agenda.utils.Constantes.START_INDEX_DIA
import com.example.agenda.utils.Constantes.START_INDEX_MES
import java.text.SimpleDateFormat
import java.util.*

class RegisterClientModel(
    val nome: ObservableField<String> = ObservableField<String>(""),
    val cpf: ObservableField<String> = ObservableField<String>(""),
    val dataCadastro : ObservableField<String> = ObservableField<String>(""),
    val dataNascimento : ObservableField<String> = ObservableField<String>(""),
    var UF : String = "",
    val telefones : ArrayList<String> = arrayListOf()
){
    fun validation() : String {

        var erros = ""

        erros += cpfValidation()
        erros += dataCadastroValidation()
        erros += dataNascValidation()
        erros += UFValidation()
        erros += telefonesValidation()

        return erros
    }
    private fun cpfValidation() : String {
        cpf.get()?.let {
            if (it.isNotEmpty() && it.length < SIZE_CPF){
                return "Nº de CPF incompleto\n"
            }
        }
        return ""
    }
    private fun dataNascValidation() : String {
        if (UF == "MG" && getIdade() < IDADE_CADASTRO_MG){
            return "Só é permitido o cadastro de maiores de idade em MG\n"
        }
        return ""
    }

    private fun dataCadastroValidation() : String {
        dataCadastro.get()?.let {
            if (it.isNotEmpty() && it.length < SIZE_DATA_CADASTRO_DEFAULT) {
                return "Data de cadastro inválida\n"
            }
        }
        return ""
    }
    private fun telefonesValidation() : String{
        return ""
    }

    private fun UFValidation() : String {
        if (UF == "SP" && cpf.get().isNullOrEmpty()){
            return "Para o estado de SP o preenchimento do CPF é obrigatório\n"
        }
        return ""
    }

    @SuppressLint("SimpleDateFormat")
    private fun getIdade() : Int{
        //formato data : dd/mm/yyyy indexs : (0,2) - dia / (3,5) - mes / (6,10) - ano

        if (dataNascimento.get().isNullOrEmpty()) return 0

        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        val df = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = df.format(calendar.time)

        val ano = currentDate.substring(START_INDEX_ANO,END_INDEX_ANO).toInt()
        val mes = currentDate.substring(START_INDEX_MES,END_INDEX_MES).toInt()
        val dia = currentDate.substring(START_INDEX_DIA, END_INDEX_DIA).toInt()

        val anoNascimento = dataNascimento.get()!!.substring(START_INDEX_ANO,END_INDEX_ANO).toInt()
        val mesNascimento = dataNascimento.get()!!.substring(START_INDEX_MES,END_INDEX_MES).toInt()
        val diaNascimento = dataNascimento.get()!!.substring(START_INDEX_DIA, END_INDEX_DIA).toInt()

        var diffAno = ano - anoNascimento
        if ( mes < mesNascimento || ((mes == mesNascimento) && (dia < diaNascimento))) {
            diffAno -= 1
        }

        return diffAno
    }
}