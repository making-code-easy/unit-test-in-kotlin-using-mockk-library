package com.vishnu.junitexample.coroutines

import com.vishnu.junitexample.coroutines.DummyData
import com.vishnu.junitexample.coroutines.MyRepository

class MyUseCase(private val repository: MyRepository) {

    suspend fun getData(): DummyData {
        repository.doSomething()
        return DummyData()
    }

    fun doHeavyWork() {
     Thread.sleep(5000)
    }

    fun doingNormal() {

    }


}