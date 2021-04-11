package com.example.agenda.ui.cadastro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.agenda.R
import com.example.agenda.databinding.FragmentCadastroBinding
import com.example.agenda.utils.Constantes

class CadastroFragment : Fragment(){

    private val viewModel : CadastroViewModel by lazy {
        ViewModelProvider(this).get(CadastroViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentCadastroBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        binding.constantes = Constantes
        insertOpcoesSpinner(Constantes.SIGLAS_ESTADO,binding.spinnerCadastroUf)
        return binding.root
    }

    fun insertOpcoesSpinner(opcoes: List<String>, spinner: Spinner){
        val adapter = getAdapterFromSpinner(opcoes)
        adapter.setDropDownViewResource(R.layout.simple_item_spinner)
        spinner.adapter = adapter
    }

    fun getAdapterFromSpinner(opcoes : List<String>) : ArrayAdapter<String>{
        return ArrayAdapter<String>(
            requireContext(),
            R.layout.simple_item_spinner,
            opcoes)
    }

}