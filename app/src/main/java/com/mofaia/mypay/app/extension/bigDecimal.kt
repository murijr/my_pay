package com.mofaia.mypay.app.extension

import java.math.BigDecimal

fun BigDecimal?.orZero() = this ?: BigDecimal.ZERO
