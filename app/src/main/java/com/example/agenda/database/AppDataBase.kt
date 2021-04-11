package com.example.agenda.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.agenda.MainActivity

abstract class AppDatabase : RoomDatabase() {

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