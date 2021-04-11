package com.example.agenda.utils

import android.widget.EditText
import androidx.databinding.BindingAdapter
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener

@BindingAdapter("mask")
fun maskEditText(editText: EditText,maskLabel: Int){
    when(maskLabel){
        Constantes.maskCPF -> {
            println("passei")
            editText.addTextChangedListener(MaskEditTextChangedListener("###.###.###-##",editText))
        }
        Constantes.maskCel -> {
            editText.addTextChangedListener(MaskEditTextChangedListener("(##)####-#####",editText))
        }
    }
}