package com.eternal.numberlist.ui.utils

import androidx.room.Room
import com.eternal.numberlist.App
import com.eternal.numberlist.database.AppDatabase
import com.eternal.numberlist.database.entity.NumberItemData
import kotlinx.coroutines.flow.map

object DatabaseUtil {

    private const val DATABASE_VERSION = 1

    private const val DATABASE_NAME = "app_database"

    private val db = Room.databaseBuilder(
        App.context,
        AppDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val numberItemDao = db.numberItemDao()

    fun loadAllNumbers() = numberItemDao.loadAll().map {
        it.toMutableList()
    }

    suspend fun insertNumber(numberItemData: NumberItemData) {
        numberItemDao.insert(numberItemData)
    }

    suspend fun deleteById(id: Long){
        numberItemDao.deleteById(id)
    }


}