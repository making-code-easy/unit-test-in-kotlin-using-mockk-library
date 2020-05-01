package com.vishnu.junitexample

import java.lang.ArithmeticException

class Calculator {

    fun divide(num1: Int, num2: Int): Int {
       return if (num2 == 0) {
            throw ArithmeticException("can not divide by zero")
        } else {
            1
        }
    }

    fun redirectDivide(num1: Int, num2: Int) {
        divide(num1,num2)
    }


    fun sendEvent() {
        MockStaticExample.showMessage()
    }
}