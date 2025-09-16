package com.georgeyt9769.baremath

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class CalculatorViewModel : ViewModel() {
    var expression by mutableStateOf("")
    var result by mutableStateOf("")

    val buttons = listOf(
        "AC", "( )", "%", "÷",
        "7", "8", "9", "×",
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "⌫", "="
    )

    fun onButtonClick(button: String) {
        when (button) {
            "AC" -> {
                expression = ""
                result = ""
            }
            "⌫" -> {
                if (expression.isNotEmpty()) {
                    expression = expression.dropLast(1)
                }
            }
            "=" -> {
                calculate()
            }
            "×" -> {
                appendOperator("*")
            }
            "÷" -> {
                appendOperator("/")
            }
            "+" -> {
                appendOperator("+")
            }
            "-" -> {
                appendOperator("-")
            }
            "( )" -> {
                handleBrackets()
            }
            else -> {
                expression += button
            }
        }
    }

    private fun appendOperator(operator: String) {
        if (expression.isNotEmpty() && expression.last().isDigit()) {
            expression += operator
        }
    }

    private fun handleBrackets() {
        val openBrackets = expression.count { it == '(' }
        val closedBrackets = expression.count { it == ')' }

        if (expression.isEmpty() || expression.last() in "/*-+" || expression.last() == '(') {
            expression += "("
        } else if (expression.last().isDigit() || expression.last() == ')') {
            if (openBrackets > closedBrackets) {
                expression += ")"
            } else {
                expression += "*("
            }
        }
    }


    private fun calculate() {
        try {
            val expr = expression.replace("×", "*").replace("÷", "/")
            val expressionBuilder = ExpressionBuilder(expr).build()
            val calculationResult = expressionBuilder.evaluate()

            if (calculationResult == calculationResult.toLong().toDouble()) {
                result = calculationResult.toLong().toString()
            } else {
                result = calculationResult.toString()
            }
        } catch (e: Exception) {
            result = "Error"
        }
    }
}