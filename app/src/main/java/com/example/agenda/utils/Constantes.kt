package com.example.agenda.utils

object Constantes {

        val maskCPF  = 1
        val maskCel  = 2
        val maskData = 3
        val maskDataCad = 4

        val START_INDEX_DIA = 0
        val START_INDEX_MES = 3
        val START_INDEX_ANO = 6
        val END_INDEX_DIA = 2
        val END_INDEX_MES = 5
        val END_INDEX_ANO = 10

        val SIZE_DATA_CADASTRO_DEFAULT = 19
        val SIZE_CPF = 14
        val IDADE_CADASTRO_MG = 18

        val SIGLAS_ESTADO = listOf<String>(
            "AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS",
            "MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC",
            "SP","SE","TO"
        )
}