package com.mofaia.mypay.app.common

import com.mofaia.mypay.app.extension.toMoney
import org.joda.money.CurrencyUnit
import java.math.RoundingMode

class CurrencyConverter {

    fun convert(amount: Double, quotation: Double)
            = (amount.toMoney().convertedTo(CurrencyUnit.USD, quotation.toMoney().amount, RoundingMode.CEILING)).amount.toDouble()

    fun getQuotation(quotationA: Double, quotationB: Double)
            = (quotationA.toMoney().dividedBy(quotationB, RoundingMode.CEILING)).amount.toDouble()

}