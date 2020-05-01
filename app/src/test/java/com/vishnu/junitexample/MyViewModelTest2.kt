package com.vishnu.junitexample

import com.vishnu.junitexample.coroutines.MyUseCase
import com.vishnu.junitexample.coroutines.MyViewModel
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Before

@ExperimentalCoroutinesApi
class MyViewModelTest2 {

    private lateinit var myUseCase: MyUseCase
    private lateinit var viewModel: MyViewModel
    private lateinit var dispatcher: TestCoroutineDispatcher


    @Before
    fun setUp() {
        dispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(dispatcher)
        myUseCase = mockk(relaxed = true)
        viewModel = spyk(MyViewModel(myUseCase))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun initializeData() {
        viewModel.initilizeData()
        coVerify { myUseCase.getData() }
    }


}