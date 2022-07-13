package com.eternal.numberlist.ui.page

import androidx.lifecycle.ViewModel
import com.eternal.numberlist.ui.utils.insert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel : ViewModel() {

    private val _listState = MutableStateFlow(mutableListOf<NumberItem<Int>>())

    val listState: StateFlow<List<NumberItem<Int>>> get() = _listState

    fun removeAt(index: Int) {
        _listState.value.removeAt(index)
    }

    fun insert(num: NumberItem<Int>) {
        _listState.value.insert(num)
    }
}

/*class HomeViewModelFactory(private val defaultList: List<NumberItem<Int>>) :
    ViewModelProvider.AndroidViewModelFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(defaultList) as T
    }
}*/