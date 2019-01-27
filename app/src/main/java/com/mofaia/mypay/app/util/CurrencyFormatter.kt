package com.mofaia.mypay.app.util

import java.text.NumberFormat

class CurrencyFormatter {

    companion object {
        @JvmStatic
        fun doubleToCurrency(number: Double): String {
            val formatter = NumberFormat.getCurrencyInstance()
            return formatter.format(number)
        }

        fun stringCurrencyToDouble(number: String): Double {
            val replacedNumber = number.replace(Regex("[^\\d]"), "")
            return replacedNumber.toDouble().div(100)
        }

    }

}