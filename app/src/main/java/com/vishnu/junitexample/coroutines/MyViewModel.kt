package com.vishnu.junitexample.coroutines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vishnu.junitexample.coroutines.DummyData
import com.vishnu.junitexample.coroutines.MyUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MyViewModel(private val myUseCase: MyUseCase) : ViewModel(),CoroutineScope {

    private val data = MutableLiveData<DummyData>()

    private val viewModelJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + viewModelJob

    fun initilizeData(){
        launch {
           data.postValue(myUseCase.getData())
        }
    }

    fun getData():LiveData<DummyData> = data




    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


}