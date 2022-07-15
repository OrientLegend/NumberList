package com.eternal.numberlist.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NumberItemData(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val number: Int,
    val brushIndex: Int
)
