package com.vishnu.junitexample.calculator

import io.mockk.*
import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {


    /*********************WILL THROW io.mockk.MockKException: no answer found *********************/
    @Test
    fun `test divide`() {
        //2.provide definition
        val calculatorHelper = mockk<CalculatorHelper>()
        val calculator = spyk(Calculator(calculatorHelper))

        //1.calling test function
        val result = calculator.divide(10, 2)

        //3.verify result
        assertEquals(5, result) // To assert the value
    }
    /**********************************************************************************************/


    /**********************************HANDLING ABOVE EXCEPTION************************************/
    @Test
    fun `test divide1`() {
        val calculatorHelper =
            mockk<CalculatorHelper>(relaxed = true) // relaxed is used to provide default answer on mockk
        val calculator = spyk(Calculator(calculatorHelper))

        val result = calculator.divide(10, 2)

        assertEquals(0, result)
        verify { calculatorHelper.divide(any(), any()) }
    }
    /**********************************************************************************************/


    /***********************************SOLVING THIS EXCEPTION*************************************/


    @Test
    fun `test divide3`() {
        val calculatorHelper = mockk<CalculatorHelper>()
        val calculator = spyk(Calculator(calculatorHelper))

        every {
            calculatorHelper.divide(
                any(),
                any()
            )
        } returns 5 // every used to provide mock defination
        //any() is matchers which can be used to
        // replace any parameter in every

        val result = calculator.divide(10, 2)
        assertEquals(5, result)
    }

    @Test
    fun `test divide31`() {
        val calculatorHelper = mockk<CalculatorHelper>()
        val calculator = spyk(Calculator(calculatorHelper))

        every {
            calculatorHelper.divide(
                any(),
                2
            )
        } returns 5 // every used to provide mock defination
        //any() is matchers which can be used to
        // replace any parameter in every

        val result = calculator.divide31(10)

        assertEquals(5, result)
    }

    /**********************************************************************************************/


    /***********************************THROWING CODE EXCEPTION*************************************/
    @Test
    fun `test divide4`() {
        val calculator = spyk(Calculator(mockk())) // It creates object with empty variable
        // used when to real class
        calculator.divide(10, 0)
    }
    /**********************************************************************************************/


    /***********************************HANDLING ABOVE EXCEPTION*************************************/
    @Test(expected = ArithmeticException::class) // expected used to assert exception
    fun `test divide5`() {
        val calculator = spyk(Calculator(mockk()))

        calculator.divide(10, 0)
    }
    /**********************************************************************************************/


    /***********************************VERIFYING METHOD CALLS*************************************/
    @Test
    fun `test divide6`() {
        val calculatorHelper = mockk<CalculatorHelper>()
        val calculator = spyk(Calculator(calculatorHelper))

        every { calculatorHelper.divide(any(), any()) } returns 5

        calculator.divide(10, 2)
        verify { calculatorHelper.divide(any(), any()) } // used to verify respective call
    }

    /**********************************************************************************************/


    /***********************************VERIFY WITH EXACTLY 0 TIMES********************************/
    @Test
    fun `test divide7`() {
        val calculatorHelper = mockk<CalculatorHelper>()
        val calculator = spyk(Calculator(calculatorHelper))

        every { calculator.divide(10, 0) } returns 0

        calculator.divide(10, 0)
        verify(exactly = 0) {
            calculatorHelper.divide(
                any(),
                any()
            )
        }// exactly is used to test check call count
    }
    /**********************************************************************************************/


    /***********************************VERIFY WITH EXACTLY 2 TIMES********************************/
    @Test
    fun `test divide8`() {
        val calculatorHelper = mockk<CalculatorHelper>()
        val calculator = spyk(Calculator(calculatorHelper))

        calculator.divide(10, 2)
        calculator.divide(10, 2)
        verify(exactly = 2) { calculatorHelper.divide(any(), any()) }
    }
    /**********************************************************************************************/


    /*****************io.mockk.MockKException: Missing calls inside every { ... } block*************/
    @Test
    fun `test sendEvent`() {
        //2. provide definition
        val calculatorHelper = mockk<CalculatorHelper>()
        val calculator = spyk(Calculator(calculatorHelper))

        every { MockStaticExample.showMessage() } just runs

        //1.calling test function
        calculator.sendEvent()

        //3.verify
        verify { MockStaticExample.showMessage() }

    }
    /**********************************************************************************************/


    /*********************************HANDLING ABOVE EXCEPTION**************************************/
    @Test
    fun `test sendEvent2`() {
        val calculatorHelper = mockk<CalculatorHelper>()
        val calculator = spyk(Calculator(calculatorHelper))

        mockkStatic(MockStaticExample::class)  // mockkStatic used to mockk static class
        every { MockStaticExample.showMessage() } just runs

        calculator.sendEvent()
        verify { MockStaticExample.showMessage() }

    }
    /**********************************************************************************************/


}