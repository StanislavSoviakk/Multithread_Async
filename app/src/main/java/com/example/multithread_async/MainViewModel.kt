package com.example.multithread_async

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel : ViewModel() {

    init {
        getRandomNumber()
    }

    private fun getRandomNumber(){
        val number = Random.nextInt(100)
    }
}