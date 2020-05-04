package com.vishnu.junitexample.coroutines

import com.vishnu.junitexample.coroutines.MyUseCase
import com.vishnu.junitexample.coroutines.MyViewModel
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Test

import org.junit.Before

@ExperimentalCoroutinesApi
class MyViewModelTest {

    private lateinit var myUseCase: MyUseCase
    private lateinit var viewModel: MyViewModel


    @Before
    fun setUp() {
        myUseCase = mockk(relaxed = true)
        viewModel = spyk(MyViewModel(myUseCase))
    }

    @After
    fun tearDown() {
        unmockkAll()
    }


    /*********** WILL THROW Exception in thread "main" java.lang.IllegalStateException**************/
    @Test
    fun `test initializeData`() {
        viewModel.initilizeData()
        coVerify { myUseCase.getData() }
    }
    /**********************************************************************************************/

}