package com.example.agenda.domain

import android.os.Parcel
import android.os.Parcelable

class Cliente(
    val id : Int?,
    val nome: String?,
    val cpf: String?,
    val dataCadastro : String?,
    val dataNascimento : String?,
    val UF : String?,
    var telefones : List<String?>?
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.createStringArrayList()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(nome)
        parcel.writeString(cpf)
        parcel.writeString(dataCadastro)
        parcel.writeString(dataNascimento)
        parcel.writeString(UF)
        parcel.writeStringList(telefones)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Cliente> {
        override fun createFromParcel(parcel: Parcel): Cliente {
            return Cliente(parcel)
        }

        override fun newArray(size: Int): Array<Cliente?> {
            return arrayOfNulls(size)
        }
    }
}