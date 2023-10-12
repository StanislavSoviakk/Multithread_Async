package com.example.multithread_async

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val THREAD_ERROR_TAG = "THREAD"

class MainViewModel : ViewModel() {

    private val _numbersList = MutableLiveData<List<Int>>()
    val numbersList: LiveData<List<Int>> = _numbersList

    private val thread: Thread by lazy {
        Thread {
            while (!Thread.currentThread().isInterrupted) {
                val number = Random.nextInt(100)
                val currentList = _numbersList.value.orEmpty().toMutableList()
                currentList.add(number)
                _numbersList.postValue(currentList)
                try {
                    Thread.sleep(2000)
                } catch (e: InterruptedException) {
                    Log.d(THREAD_ERROR_TAG, e.message.toString())
                }
            }
        }
    }

    init {
        getRandomNumber()
    }

    private fun getRandomNumber() {
        thread.start()
    }

    override fun onCleared() {
        thread.interrupt()
        super.onCleared()
    }
}