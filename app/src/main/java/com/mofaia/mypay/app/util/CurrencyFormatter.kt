package com.mofaia.mypay.app.util

import java.math.BigDecimal
import java.text.NumberFormat

class CurrencyFormatter {

    companion object {
        @JvmStatic
        fun doubleToCurrency(number: BigDecimal): String {
            val formatter = NumberFormat.getCurrencyInstance()
            return formatter.format(number)
        }

        @JvmStatic
        fun doubleToCurrency(number: BigDecimal, maximumFractionDigits: Int): String {
            val formatter = NumberFormat.getCurrencyInstance()
            formatter.maximumFractionDigits = maximumFractionDigits
            return formatter.format(number)
        }

        fun stringCurrencyToBigDecimal(number: String): BigDecimal {
            val replacedNumber = number.replace(Regex("[^\\d]"), "")
            return replacedNumber.toBigDecimal().divide(BigDecimal(100))
        }

    }

}