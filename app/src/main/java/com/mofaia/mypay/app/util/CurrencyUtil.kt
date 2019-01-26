package com.mofaia.mypay.app.util

import java.text.NumberFormat

class CurrencyUtil {

    companion object {
        @JvmStatic
        fun doubleToCurrency(number: Double): String {
            val formatter = NumberFormat.getCurrencyInstance()
            return formatter.format(number)
        }
    }

}