package com.mofaia.mypay.app.common

import com.mofaia.mypay.app.extension.toMoney

class CurrencyConverter {

    fun convert(amount: Double, quotation: Double)
            = (amount.toMoney().amount * quotation.toMoney().amount).toDouble()

}