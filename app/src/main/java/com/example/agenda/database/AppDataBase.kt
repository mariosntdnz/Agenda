package com.example.agenda.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agenda.MainActivity
import com.example.agenda.database.dao.ClientesDao
import com.example.agenda.database.entity.ClienteEntity
import com.example.agenda.database.entity.TelefoneEntity

@Database(
    entities = [
        ClienteEntity::class,TelefoneEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun clientesDao() : ClientesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase (): AppDatabase? {
            if (this.INSTANCE != null) {
                return this.INSTANCE
            } else {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        MainActivity.instance,
                        AppDatabase::class.java,
                        "agenda")
                        .build()
                    this.INSTANCE = instance
                    return instance
                }
            }
        }
    }
}