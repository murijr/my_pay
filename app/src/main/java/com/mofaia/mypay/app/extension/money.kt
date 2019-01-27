package com.mofaia.mypay.app.extension

import org.joda.money.BigMoney
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.BigDecimal
import java.math.RoundingMode

fun Double.toMoney(currency: CurrencyUnit = CurrencyUnit.of("BRL")): Money {
    return BigMoney.of(currency, this).toMoney(RoundingMode.DOWN)
}

fun String.toMoney(currency: CurrencyUnit = CurrencyUnit.of("BRL")): Money {
    return BigMoney.of(currency, this.toBigDecimal()).toMoney(RoundingMode.DOWN)
}

fun BigDecimal.toMoney(currency: CurrencyUnit = CurrencyUnit.of("BRL")): Money {
    return BigMoney.of(currency, this).toMoney(RoundingMode.DOWN)
}