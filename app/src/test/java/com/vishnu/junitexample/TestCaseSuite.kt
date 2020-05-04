package com.vishnu.junitexample

import com.vishnu.junitexample.coroutines.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    Calculator::class,
    CarManufacturerTest::class,
    MyUseCaseTest::class,
    MyUseCaseWithInjectedDispatcherTest::class,
    MyViewModelTest::class,
    MyViewModelTest2::class
)
class TestCaseSuite