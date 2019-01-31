package com.mofaia.mypay.app.common

import java.text.SimpleDateFormat
import java.util.*

class DateManipulator(private val locale: Locale) {

    fun getCurrentDate(format: String = "MM-dd-yyyy"): String {
        val date = Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat(format, locale)
        return simpleDateFormat.format(date).toString()
    }

    fun formatt(date: Date, format: String = "MM-dd-yyyy H:mm:s"): String {
        val simpleDateFormat = SimpleDateFormat(format, locale)
        return simpleDateFormat.format(date).toString()
    }

}