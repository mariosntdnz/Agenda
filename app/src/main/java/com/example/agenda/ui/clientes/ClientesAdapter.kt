package com.example.agenda.ui.clientes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.databinding.ClientesItemBinding
import com.example.agenda.domain.Cliente

class ClientesAdapter : PagingDataAdapter<Cliente, ClientesAdapter.ClienteViewHolder>(DiffCallback){

    class ClienteViewHolder(val binding: ClientesItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    companion object DiffCallback : DiffUtil.ItemCallback<Cliente>() {
        override fun areItemsTheSame(oldItem: Cliente , newItem: Cliente): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Cliente, newItem: Cliente): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onBindViewHolder(holder: ClientesAdapter.ClienteViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClienteViewHolder {
        return ClienteViewHolder(ClientesItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
}