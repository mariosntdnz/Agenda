package com.example.agenda.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import br.com.jansenfelipe.androidmask.MaskEditTextChangedListener
import com.example.agenda.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("mask")
fun maskEditText(editText: EditText,maskLabel: Int){
    when(maskLabel){
        Constantes.maskCPF -> {
            editText.addTextChangedListener(MaskEditTextChangedListener("###.###.###-##",editText))
        }
        Constantes.maskCel -> {
            editText.addTextChangedListener(MaskEditTextChangedListener("(##)####-#####",editText))
        }
        Constantes.maskData->{
            editText.addTextChangedListener(MaskEditTextChangedListener("##/##/####",editText))
        }
        Constantes.maskDataCad ->{
            editText.addTextChangedListener(MaskEditTextChangedListener("##/##/#### ##:##:##",editText))
        }
    }
}

@SuppressLint("SimpleDateFormat")
@BindingAdapter("getData")
fun getData(editText: EditText,status: Boolean){
    val calendario = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    val df = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
    editText.setOnClickListener {
        if (editText.text.toString().isEmpty()){
            editText.setText(df.format(calendario.time))
            editText.isFocusableInTouchMode = true
        }
    }
}

@SuppressLint("SetTextI18n")
@BindingAdapter("plotCalendar")
fun plotDialogCalendar(editText: EditText,status : Boolean){
    if (status) {
        val calendario = Calendar.getInstance()

        editText.setOnClickListener {
            val datePickerDialog = DatePickerDialog(editText.context,R.style.DialogTheme,
                    { view, year, month, dayOfMonth ->
                        val monthReal = month + 1
                        val dayOfMonthLabel = if (dayOfMonth >= 10) dayOfMonth.toString() else "0$dayOfMonth"
                        val monthLabel = if (monthReal >= 10) monthReal.toString() else "0$monthReal"
                        editText.setText("")
                        editText.setText("$dayOfMonthLabel$monthLabel$year")
                    }, calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH), calendario.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }
    }
}

@BindingAdapter("insertOpcoes")
fun insertOpcoesSpinner(spinner: Spinner,opcoes : List<String>){
        val adapter = ArrayAdapter<String>(spinner.context, R.layout.simple_item_spinner, arrayOf("Selecione") + opcoes)
        adapter.setDropDownViewResource(R.layout.simple_item_spinner)
        spinner.adapter = adapter
}