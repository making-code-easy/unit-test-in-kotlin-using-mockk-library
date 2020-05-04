package com.vishnu.junitexample.coroutines

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
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MyUseCaseTest {

    private lateinit var repository: MyRepository
    private lateinit var myUseCase: MyUseCase


    private lateinit var repositoryNew: MyRepository
    private lateinit var myUseCaseNew: MyUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        repository = mockk()
        myUseCase = spyk(MyUseCase(repository))


        repositoryNew = mockk(relaxed = true)
        myUseCaseNew = spyk(MyUseCase(repositoryNew))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        unmockkAll()
    }

    /****************WILL THROW io.mockk.MockKException: no answer found***************************/
    @Test
    fun `test getData`() {
        runBlockingTest {
            myUseCase.getData()
            coVerify { repository.doSomething() }
        }
    }
    /**********************************************************************************************/

    /*************************HANDEL ABOVE EXCEPTION WITH coEvery**********************************/
    @Test
    fun `test getData2`() {
        runBlockingTest {
            coEvery { repository.doSomething() } just runs
            myUseCase.getData()
            coVerify { repository.doSomething() }
        }
    }
    /**********************************************************************************************/


    /*************HANDEL ABOVE EXCEPTION by making relaxed repository object **********************/
    @Test
    fun `test getData3`() {
        runBlockingTest {
            myUseCaseNew.getData()
            coVerify { repositoryNew.doSomething() }
        }
    }
    /**********************************************************************************************/






    /*************************TESTING COROUTINES WITH runBlocking**********************************/
    @Test
    fun `test heavyOperation`() {
        runBlocking {//will run normally, takes required time

            val expected = 666666671666
            val result = myUseCase.heavyOperation()
            assertEquals(expected, result)
        }
    }
    /**********************************************************************************************/


    /*************************TESTING COROUTINES WITH runBlocking**********************************/
    @Test
    fun `test heavyOperationWithDelay`() {
        runBlocking {//will run normally, takes required time 15 seconds to run

            val expected = 666666671666
            val result = myUseCase.heavyOperationWithDelay()
            assertEquals(expected, result)
        }
    }
    /**********************************************************************************************/


    /*************************TESTING COROUTINES WITH runBlockingTest******************************/
    @Test
    fun `test heavyOperation2`() {
        runBlockingTest {//will run by autoAdvancing time, runs fast

            val expected = 666666671666
            val result = myUseCase.heavyOperation()
            assertEquals(expected, result)
        }
    }
    //failing due to autoAdvancing time feature
    /**********************************************************************************************/



}