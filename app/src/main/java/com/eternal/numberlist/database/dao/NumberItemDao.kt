package com.eternal.numberlist.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eternal.numberlist.database.entity.NumberItemData
import kotlinx.coroutines.flow.Flow

@Dao
interface NumberItemDao {

    @Insert
    suspend fun insert(numberItemData: NumberItemData): Long

    @Update
    suspend fun update(numberItemData: NumberItemData)

    @Query("SELECT * FROM NumberItemData")
    fun loadAll(): Flow<List<NumberItemData>>

    @Query("DELETE FROM NumberItemData WHERE id = :id")
    suspend fun deleteById(id: Long): Int


}