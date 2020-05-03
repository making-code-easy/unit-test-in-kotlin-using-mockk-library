package com.vishnu.junitexample.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.math.sqrt

class MyUseCaseWithInjectedDispatcher(private val repository: MyRepository,private val dispatcher: CoroutineDispatcher) {

    suspend fun heavyOperation(): Long {
        return withContext(dispatcher) {
            return@withContext doHardMaths()
        }
    }

    suspend fun heavyOperationWithDelay(): Long {
        return withContext(dispatcher) {
            delay(15_000)
            return@withContext doHardMaths()
        }
    }

    private fun doHardMaths(): Long {
        var count = 0.0
        for (i in 1..100_000_000) {
            count += sqrt(i.toDouble())
        }
        return count.toLong()
    }


}