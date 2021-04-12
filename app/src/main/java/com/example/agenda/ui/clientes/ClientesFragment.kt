package com.example.agenda.ui.clientes

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.agenda.databinding.FragmentClientesBinding

class ClientesFragment : Fragment(){

    private val viewModel : ClientesViewModel by lazy {
        ViewModelProvider(this).get(ClientesViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentClientesBinding.inflate(inflater,container,false)
        val adapter = ClientesAdapter()

        binding.lifecycleOwner = this
        binding.recyclerViewClientes.adapter = adapter

        binding.fab.setOnClickListener {
            this.findNavController().navigate(ClientesFragmentDirections.actionClientesFragmentToCadastroFragment())
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}