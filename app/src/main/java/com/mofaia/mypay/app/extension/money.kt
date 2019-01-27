package com.mofaia.mypay.app.extension

import org.joda.money.BigMoney
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.math.RoundingMode

fun Double.round(places: Int): Double {
    var value = this
    if (places < 0) throw IllegalArgumentException()

    val factor = Math.pow(10.0, places.toDouble()).toLong()
    value *= factor
    val tmp = Math.round(value)
    return tmp.toDouble() / factor
}

fun Double.toMoney(currency: CurrencyUnit = CurrencyUnit.of("BRL")): Money {
    return BigMoney.of(currency, this).toMoney(RoundingMode.DOWN)
}