package com.example.agenda.ui.clientes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.databinding.ClientesItemBinding
import com.example.agenda.domain.Cliente
import kotlinx.android.synthetic.main.clientes_item.view.*

class ClientesAdapter : ListAdapter<Cliente, ClientesAdapter.ClienteViewHolder>(DiffCallback){

    class ClienteViewHolder(binding: ClientesItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object DiffCallback : DiffUtil.ItemCallback<Cliente>() {
        override fun areItemsTheSame(oldItem: Cliente , newItem: Cliente): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Cliente, newItem: Cliente): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) {
        val cliente = getItem(position)
        println(cliente.nome + "DIFF")
        holder.itemView.textViewNomeCliente.text = cliente.nome
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClienteViewHolder {
        return ClienteViewHolder(ClientesItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
}