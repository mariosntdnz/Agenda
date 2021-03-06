package com.example.agenda.database.dao

import androidx.room.*
import com.example.agenda.database.entity.ClienteEntity
import com.example.agenda.database.entity.ClienteWithTelefone
import com.example.agenda.database.entity.TelefoneEntity
import com.example.agenda.domain.Cliente
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCliente(cliente : ClienteEntity) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTelefones(generoIDEntity: List<TelefoneEntity?>?)

    @Delete
    fun deleteCliente(cliente: ClienteEntity)

    @Transaction
    @Query("SELECT * FROM cliente")
    fun getClienteWithTelefone(): Flow<List<ClienteWithTelefone>>
}