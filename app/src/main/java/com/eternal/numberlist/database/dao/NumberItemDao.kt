package com.eternal.numberlist.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eternal.numberlist.database.entity.NumberItemData

@Dao
interface NumberItemDao {

    @Insert
    fun insert(numberItemData: NumberItemData): Long

    @Update
    fun update(numberItemData: NumberItemData)

    @Query("SELECT * FROM NumberItemData")
    fun loadAll(): List<NumberItemData>

    @Query("DELETE FROM NumberItemData WHERE id = :id")
    fun deleteById(id: Long): Int
}