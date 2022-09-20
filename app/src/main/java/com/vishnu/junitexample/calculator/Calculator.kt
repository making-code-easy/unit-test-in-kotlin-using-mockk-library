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

    fun divide31(num1: Int): Int {
        return calculatorHelper.divide(num1, 2)
    }

    fun sendEvent() {
        MockStaticExample.showMessage()
    }
}