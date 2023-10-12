package com.example.multithread_async

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel : ViewModel() {

    val numbersListFlow = MutableStateFlow(emptyList<Int>())

    init {
        getRandomNumber()
    }

    private fun getRandomNumber(){
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                val number = Random.nextInt(100)
                val currentList = numbersListFlow.value.toMutableList()
                currentList.add(number)
                numbersListFlow.value = currentList
                delay(2000)
            }
        }
    }
}