package com.example.multithread_async

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val numbersSubject = BehaviorSubject.createDefault(emptyList<Int>())

    val numbersObservable: Observable<List<Int>> = numbersSubject.hide()


    init {
        getRandomNumber()
    }

    private fun getRandomNumber() {
        val randomNumberObservable = Observable.interval(2, TimeUnit.SECONDS)
            .map { Random.nextInt(100) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        val disposable = randomNumberObservable.subscribe { number ->
            val currentList = numbersSubject.value.orEmpty().toMutableList()
            currentList.add(number)
            numbersSubject.onNext(currentList)
        }

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}