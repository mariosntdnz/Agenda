package com.example.agenda.ui.cadastro

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.agenda.R
import com.example.agenda.database.repository.ClientesRepository
import com.example.agenda.databinding.FragmentCadastroBinding
import com.example.agenda.utils.Constantes


class CadastroFragment : Fragment(){

    private val viewModel : CadastroViewModel by lazy {
        ViewModelProvider(this, CadastroViewModelFactory(ClientesRepository())).get(
                CadastroViewModel::class.java
        )
    }

    lateinit var binding: FragmentCadastroBinding
    lateinit var adapter: CadastroTelefoneAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCadastroBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.constantes = Constantes
        binding.cliente = viewModel.registerClientModel

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CadastroTelefoneAdapter(CadastroTelefoneAdapter.OnClickListener { telefone: String, index: Int ->
            viewModel.insertTelefone(telefone, index)

        })

        val listaTelefone = arrayListOf("")

        adapter.submitList(listaTelefone)
        binding.recyclerView.adapter = adapter

        binding.buttonCadastroAddTelefone.setOnClickListener {
            listaTelefone.add("")
            adapter.submitList(listaTelefone)
            adapter.notifyItemInserted(listaTelefone.size-1)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.salvar -> {
                viewModel.registerClientModel.UF = binding.spinnerCadastroUf.selectedItem.toString()

                val erros = viewModel.registerClientModel.validation()
                if (erros == "") {
                    viewModel.insertClienteBD()
                    findNavController().navigateUp()
                } else {
                    plotAlertDialogErros(erros)
                }

                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }

        }
    }

    fun plotAlertDialogErros(message: String){
        AlertDialog.Builder(requireContext()) //set icon
            .setIcon(R.drawable.ic_error_dialog)
            .setTitle(R.string.cadastro_error)
            .setMessage(message)
            .setPositiveButton(
                    R.string.cadastro_dialog_positive_message
            ) { _, _ -> }
            .show()
    }

}