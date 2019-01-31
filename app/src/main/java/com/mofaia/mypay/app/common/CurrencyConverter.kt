package com.mofaia.mypay.app.common

import java.math.BigDecimal

class CurrencyConverter {

    fun convert(amount: BigDecimal, quotation: BigDecimal)
            = amount * quotation

    fun getQuotation(quotationA: BigDecimal, quotationB: BigDecimal)
            = quotationA / quotationB

}