package com.eternal.numberlist.ui.utils

import com.eternal.numberlist.database.entity.NumberItemData

/**
 * Binary insert for ordered list
 * @warning Can only be used when the list is ordered
 */
fun MutableList<NumberItemData>.insert(num: NumberItemData) {
    if(this.isEmpty()) {
        this.add(num)
        return
    }
    var left = 0
    var right = this.size - 1
    while(left <= right){
        val mid = (left + right) shr 1;
        if(this[mid].number < num.number) {
            left = mid + 1
        } else if (this[mid].number > num.number) {
            right = mid - 1
        } else {
            this.add(index = mid, element = num)
            return
        }
    }
    this.add(index = left, element = num)
}