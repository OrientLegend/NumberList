package com.eternal.numberlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.eternal.numberlist.database.dao.NumberItemDao
import com.eternal.numberlist.database.entity.NumberItemData

@Database(
    version = AppDatabase.DATABASE_VERSION,
    entities = [NumberItemData::class],
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun numberItemDao(): NumberItemDao

    companion object {
        const val DATABASE_VERSION = 1

        private var instance: AppDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): AppDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "app_database"
            ).build().apply {
                instance = this
            }
        }

    }

}