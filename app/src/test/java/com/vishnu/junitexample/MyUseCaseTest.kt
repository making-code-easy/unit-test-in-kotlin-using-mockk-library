package com.vishnu.junitexample

import com.vishnu.junitexample.coroutines.MyRepository
import com.vishnu.junitexample.coroutines.MyUseCase
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Test

import org.junit.Before

@ExperimentalCoroutinesApi
class MyUseCaseTest {

    private lateinit var repository: MyRepository
    private lateinit var myUseCase: MyUseCase

    @Before
    fun setUp(){
        repository = mockk(relaxed = true)
        myUseCase = spyk(MyUseCase(repository))
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getData() {
        runBlockingTest {
            myUseCase.getData()
            coVerify { repository.doSomething() }
        }

    }
}