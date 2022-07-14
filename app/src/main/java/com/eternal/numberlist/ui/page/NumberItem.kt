package com.eternal.numberlist.ui.page

import com.eternal.numberlist.database.entity.NumberItemData


data class NumberItem(var id: Long = 0, val number: Int, val brushIndex: Int){
    constructor(numberItemData: NumberItemData) : this(
        numberItemData.id,
        numberItemData.number,
        numberItemData.brushIndex
    )
}