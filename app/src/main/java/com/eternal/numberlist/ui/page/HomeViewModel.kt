package com.eternal.numberlist.ui.page

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eternal.numberlist.database.entity.NumberItemData
import com.eternal.numberlist.ui.utils.DatabaseUtil
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _listState = MutableStateFlow(flowOf<List<NumberItemData>>())

    val listState: StateFlow<Flow<List<NumberItemData>>> get() = _listState.asStateFlow()

    init {
        _listState.value = DatabaseUtil.loadAllNumbers().map {
            it.sortedBy { numberItemData ->
                numberItemData.number
            }
        }
    }

    fun removeById(id: Long) {
        Log.e("RemoveById", id.toString())
        viewModelScope.launch {
            DatabaseUtil.deleteById(id)
        }

    }

    fun insert(num: NumberItemData) {
        viewModelScope.launch {
            DatabaseUtil.insertNumber(num)
        }

    }
}