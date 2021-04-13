package com.example.agenda.ui.detalhes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.databinding.DetalheItemBinding
import kotlinx.android.synthetic.main.detalhe_item.view.*

class DetalhesTelefoneAdapter(private val onClickListener: OnClickListener) : ListAdapter<String, DetalhesTelefoneAdapter.DetalheTelefoneViewHolder>(DiffCallback){

    class DetalheTelefoneViewHolder(binding: DetalheItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: DetalheTelefoneViewHolder, position: Int) {
        val telefone = getItem(position)

        holder.itemView.textViewTelefone.text = telefone
        println(telefone)
        holder.itemView.imageView.setOnClickListener {
            onClickListener.numeroClicado(telefone)
        }
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): DetalheTelefoneViewHolder {
        return DetalheTelefoneViewHolder(DetalheItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class OnClickListener(val clickListener: (text: String) -> Unit) {
        fun numeroClicado(telefone: String) = clickListener(telefone)
    }
}