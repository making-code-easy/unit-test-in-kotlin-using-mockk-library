package com.vishnu.junitexample.coroutines

import io.mockk.mockk
import io.mockk.spyk
import io.mockk.unmockkAll
import junit.framework.Assert
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

@ExperimentalCoroutinesApi
class MyUseCaseWithInjectedDispatcherTest {

    private lateinit var repository: MyRepository
    private lateinit var myUseCase: MyUseCaseWithInjectedDispatcher
    private lateinit var dispatcher: TestCoroutineDispatcher

    @Before
    fun setUp() {
        dispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(dispatcher)
        repository = mockk(relaxed = true)
        myUseCase = spyk(MyUseCaseWithInjectedDispatcher(repository, dispatcher))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    /*************************TESTING COROUTINES WITH runBlockingTest******************************/
    @Test
    fun heavyOperation() {
        runBlockingTest {
            val expected = 666666671666
            val result = myUseCase.heavyOperation()
            assertEquals(expected, result)
        }
    }
    //passed due to same dispatcher
    /**********************************************************************************************/


    /*************************TESTING COROUTINES WITH runBlockingTest******************************/
    @Test
    fun heavyOperationWithDelay() {
        runBlockingTest {
            val expected = 666666671666
            val result = myUseCase.heavyOperationWithDelay()
            assertEquals(expected, result)
        }
    }
    // it will fail, because of autoAdvancing feature
    /**********************************************************************************************/


    /********************TESTING COROUTINES WITH dispatcher.runBlockingTest************************/
    @Test
    fun heavyOperationWithDelay2() {
        dispatcher.runBlockingTest {
            val expected = 666666671666
            val result = myUseCase.heavyOperationWithDelay()
            assertEquals(expected, result)
        }
    }
    /**********************************************************************************************/


}