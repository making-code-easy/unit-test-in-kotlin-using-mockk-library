package com.vishnu.junitexample

import io.mockk.*
import org.junit.Test

import org.junit.Assert.*
import java.lang.ArithmeticException

class CalculatorTest {


    /*********************WILL THROW io.mockk.MockKException: no answer found *********************/
    @Test
    fun `test divide`() {
        val calculator = mockk<Calculator>() //To mockk object to test
        val result = calculator.divide(10, 2)
        assertEquals(5, result) // To assert the value
    }
    /**********************************************************************************************/


    /**********************************HANDLING ABOVE EXCEPTION************************************/
    @Test
    fun `test divide1`() {
        val calculator = mockk<Calculator>(relaxed = true) // relaxed is used to provide default answer on mockk
        val result = calculator.divide(10, 2)
        assertEquals(0, result)
    }
    /**********************************************************************************************/


    /***********************************SOLVING THIS EXCEPTION*************************************/
    @Test
    fun `test divide3`() {
        val calculator = mockk<Calculator>()
        every { calculator.divide(any(), any()) } returns 5 // every used to provide mock defination
                                                            //any() is matchers which can be used to
                                                            // replace any parameter in every
        val result = calculator.divide(10, 2)
        assertEquals(5, result)
    }
    /**********************************************************************************************/


    /***********************************THROWING CODE EXCEPTION*************************************/
    @Test
    fun `test divide4`() {
        val calculator = spyk(Calculator()) // It creates object with empty variable
                                                     // used when to real class
        val result = calculator.divide(10, 0)
    }
    /**********************************************************************************************/


    /***********************************HANDLING ABOVE EXCEPTION*************************************/
    @Test(expected = ArithmeticException::class) // expected used to assert exception
    fun `test divide5`() {
        val calculator = spyk(Calculator())
        calculator.divide(10, 0)
    }
    /**********************************************************************************************/


    /***********************************VERIFYING METHOD CALLS*************************************/
    @Test
    fun `test divide6`() {
        val calculator = mockk<Calculator>()
        every { calculator.divide(any(), any()) } returns 5
        calculator.divide(10,2)
        verify { calculator.divide(any(),any()) } // used to verify respective call
    }

    /**********************************************************************************************/


    /***********************************VERIFY WITH EXACTLY 0 TIMES********************************/
    @Test
    fun `test divide7`() {
        val calculator = mockk<Calculator>()
        every { calculator.redirectDivide(10,0) } just runs
        calculator.redirectDivide(10, 0)
        verify(exactly = 0) { calculator.divide(any(), any()) }// exactly is used to test check call count
    }
    /**********************************************************************************************/


    /***********************************VERIFY WITH EXACTLY 2 TIMES********************************/
    @Test
    fun `test divide8`() {
        val calculator = spyk(Calculator())
        calculator.redirectDivide(10, 2)
        calculator.redirectDivide(10, 2)
        verify(exactly = 2) { calculator.divide(any(), any()) }
    }
    /**********************************************************************************************/


    /*****************io.mockk.MockKException: Missing calls inside every { ... } block*************/
    @Test
    fun `test sendEvent`(){
        val calculator = mockk<Calculator>()
        every { MockStaticExample.showMessage() } just runs
        calculator.sendEvent()
        verify { calculator.sendEvent() }

    }

    /**********************************************************************************************/

    /*********************************HANDLING ABOVE EXCEPTION**************************************/
    @Test
    fun `test sendEvent2`() {
        val calculator = mockk<Calculator>()
        mockkStatic(MockStaticExample::class)  // mockkStatic used to mockk static class
        every { MockStaticExample.showMessage() } just runs
        every { calculator.sendEvent() } just runs
        calculator.sendEvent()
        verify { calculator.sendEvent() }

    }

    /**********************************************************************************************/




}