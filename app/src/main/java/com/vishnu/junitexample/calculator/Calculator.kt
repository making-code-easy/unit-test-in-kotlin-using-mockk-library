package com.vishnu.junitexample.calculator

import java.lang.ArithmeticException

class Calculator(private val calculatorHelper: CalculatorHelper) {

    fun divide(num1: Int, num2: Int): Int {
        return if (num2 == 0) {
            throw ArithmeticException("can not divide by zero")
        } else {
              calculatorHelper.divide(num1, num2)
        }
    }
fun divide2(num1: Int, num2: Int): String {
        return if (num2 == 0) {
            throw ArithmeticException("can not divide by zero")
        } else {
              calculatorHelper.divide2(num1, num2)
        }
    }

    fun redirectDivide(num1: Int, num2: Int) {
        divide(num1, num2)
    }


    fun sendEvent() {
        MockStaticExample.showMessage()
    }
}