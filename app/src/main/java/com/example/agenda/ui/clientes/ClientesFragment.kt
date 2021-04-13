package com.example.agenda.ui.clientes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.agenda.database.repository.ClientesRepository
import com.example.agenda.databinding.FragmentClientesBinding
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

        val adapter = ClientesAdapter(ClientesAdapter.OnClickListener {
            viewModel.setContatoClicado(it)
        })

        binding.recyclerViewClientes.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.clientes.collect {
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}