package com.example.agenda.ui.clientes

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.agenda.R
import com.example.agenda.database.repository.ClientesRepository
import com.example.agenda.databinding.FragmentClientesBinding
import com.example.agenda.domain.Cliente
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ClientesFragment : Fragment(){

    val viewModel : ClientesViewModel by lazy {
        ViewModelProvider(this, ClientesViewModelFactory(ClientesRepository())).get(
            ClientesViewModel::class.java
        )
    }

    lateinit var binding : FragmentClientesBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClientesBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this

        binding.fab.setOnClickListener {
            this.findNavController().navigate(ClientesFragmentDirections.actionClientesFragmentToCadastroFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ClientesAdapter(ClientesAdapter.OnClickListener (
                {viewModel.setContatoClicado(it) },
                {deleteCliente(it)}
        ))

        binding.recyclerViewClientes.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.clientes.collect {

                if(it.size > 0) binding.textViewSemConatos.visibility = View.GONE
                if(it.size == 0) binding.textViewSemConatos.visibility = View.VISIBLE

                adapter.submitList(it.sortedBy { it.nome })
            }
        }

        viewModel.contatoClicado.observe(viewLifecycleOwner, {
            it?.let {
                this.findNavController().navigate(ClientesFragmentDirections.actionClientesFragmentToDetalhesFragment(it))
                viewModel.navigationTelaDetalhes()

            }
        })

    }

    fun deleteCliente(cliente : Cliente){
        AlertDialog.Builder(requireContext()) //set icon
                .setIcon(R.drawable.ic_error_dialog)
                .setTitle(getString(R.string.confirmar_excluir_contato,cliente.nome))
                .setPositiveButton(
                        R.string.default_positive_button
                ) { _, _ ->
                    viewModel.deleteCliente(cliente)
                }
                .setNegativeButton(
                        R.string.default_negative_button
                ){ _, _-> }
                .show()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}