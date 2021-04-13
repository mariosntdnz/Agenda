package com.example.agenda.ui.detalhes

import android.Manifest.permission.CALL_PHONE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.agenda.databinding.FragmentDetalhesBinding
import com.example.agenda.domain.Cliente


class DetalhesFragment : Fragment() {

    lateinit var binding : FragmentDetalhesBinding
    lateinit var cliente : Cliente

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        binding = FragmentDetalhesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        cliente = DetalhesFragmentArgs.fromBundle(requireArguments()).contato
        binding.cliente = cliente

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DetalhesTelefoneAdapter(DetalhesTelefoneAdapter.OnClickListener {
            discarNumero(it)
        })

        binding.recyclerViewDetalhesTelefones.adapter = adapter

        if (cliente.telefones?.get(0) != ""){
            adapter.submitList(cliente.telefones)
        }

    }

    fun discarNumero(telefone: String){
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:" + telefone)
        if (ContextCompat.checkSelfPermission(requireContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(callIntent)
        } else {
            requestPermissions(requireActivity(),arrayOf(CALL_PHONE), 1);
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

}