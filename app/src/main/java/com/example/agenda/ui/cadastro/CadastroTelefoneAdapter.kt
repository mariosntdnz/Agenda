package com.example.agenda.ui.cadastro

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.agenda.databinding.TelefoneItemBinding
import com.example.agenda.utils.Constantes
import kotlinx.android.synthetic.main.telefone_item.view.*


class CadastroTelefoneAdapter(private val onClickListener: OnClickListener) : ListAdapter<String, CadastroTelefoneAdapter.TelefoneViewHolder>(DiffCallback){

    class TelefoneViewHolder(binding: TelefoneItemBinding) : RecyclerView.ViewHolder(binding.root){
        init{
            binding.constantes = Constantes
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: TelefoneViewHolder, position: Int) {
        val telefone = getItem(position)

        holder.itemView.editTextPhone.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {

            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun afterTextChanged(s: Editable) {
                onClickListener.setTelefoneText(s.toString(),position)
            }
        })

        holder.itemView.editTextPhone.setText(telefone)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): TelefoneViewHolder {
        return TelefoneViewHolder(TelefoneItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    class OnClickListener(val clickListener: (text: String, position: Int) -> Unit) {
        fun setTelefoneText(telefone: String, index: Int) = clickListener(telefone, index)
    }
}