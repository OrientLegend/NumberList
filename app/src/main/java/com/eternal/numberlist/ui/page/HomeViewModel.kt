package com.eternal.numberlist.ui.page

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eternal.numberlist.App.Companion.appDatabase
import com.eternal.numberlist.database.entity.NumberItemData
import com.eternal.numberlist.ui.utils.insert
import com.eternal.numberlist.ui.utils.runOnMainThread
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class HomeViewModel : ViewModel() {

    private val _listState = MutableStateFlow(mutableStateListOf<NumberItem>())

    val listState: StateFlow<List<NumberItem>> get() = _listState.asStateFlow()

    init {
        thread {
            val recoverList = appDatabase.numberItemDao().loadAll().map {
                NumberItem(it)
            }.toMutableStateList()
            runOnMainThread {
                _listState.value = recoverList
            }
        }
    }

    fun removeAt(index: Int) {
        val id = _listState.value[index].id
        _listState.value.removeAt(index)
        thread {
            appDatabase.numberItemDao().deleteById(id)
        }
    }

    fun insert(num: NumberItem) {
        _listState.value.insert(num)
        thread {
            num.id = appDatabase.numberItemDao().insert(
                NumberItemData(
                    number = num.number,
                    brushIndex = num.brushIndex
                )
            )
        }
    }
}