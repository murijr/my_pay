package com.mofaia.mypay.app.extension

import org.joda.money.BigMoney
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.RoundingMode

fun Double.toMoney(currency: CurrencyUnit = CurrencyUnit.of("BRL")): Money {
    return BigMoney.of(currency, this).toMoney(RoundingMode.CEILING)
}