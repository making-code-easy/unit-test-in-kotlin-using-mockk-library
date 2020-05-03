package com.vishnu.junitexample.coroutines

import com.vishnu.junitexample.coroutines.MyRepository
import com.vishnu.junitexample.coroutines.MyUseCase
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Before

@ExperimentalCoroutinesApi
class MyUseCaseTest {

    private lateinit var repository: MyRepository
    private lateinit var myUseCase: MyUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        repository = mockk(relaxed = true)
        myUseCase = spyk(MyUseCase(repository))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    @Test
    fun getData() {
        runBlockingTest {
            myUseCase.getData()
            coVerify { repository.doSomething() }
        }

    }


    /*************************TESTING COROUTINES WITH runBlocking**********************************/
    @Test
    fun heavyOperation() {
        runBlocking {//will run normally, takes required time

            val expected = 666666671666
            val result = myUseCase.heavyOperation()
            assertEquals(expected, result)
        }
    }
    /**********************************************************************************************/


    /*************************TESTING COROUTINES WITH runBlocking**********************************/
    @Test
    fun heavyOperationWithDelay() {
        runBlocking {//will run normally, takes required time 15 seconds to run

            val expected = 666666671666
            val result = myUseCase.heavyOperationWithDelay()
            assertEquals(expected, result)
        }
    }
    /**********************************************************************************************/


    /*************************TESTING COROUTINES WITH runBlockingTest******************************/
    @Test
    fun heavyOperation2() {
        runBlockingTest {//will run by autoAdvancing time, runs fast

            val expected = 666666671666
            val result = myUseCase.heavyOperation()
            assertEquals(expected, result)
        }
    }
    //failing due to autoAdvancing time feature
    /**********************************************************************************************/



}