package com.mofaia.mypay.app.extension

import java.math.BigDecimal

fun BigDecimal?.orEmpty() = this ?: BigDecimal.ZERO
