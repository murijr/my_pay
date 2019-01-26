package com.mofaia.mypay.app.common

import java.text.SimpleDateFormat
import java.util.*

class DateManipulator(private val locale: Locale) {

    fun getCurrentDate(format: String = "MM-dd-yyyy"): String {
        val date = Calendar.getInstance().time
        val simpleDateFormat = SimpleDateFormat(format, locale)
        return simpleDateFormat.format(date).toString()
    }

    fun unixTimeStampToDate(unixTimeStamp: Long) = Date(unixTimeStamp * 1000)

    fun stringToDate(format: String = "yyyy-MM-dd H:m:s.S", dateString: String): Date {

        val formatter = SimpleDateFormat(format, locale)
        return formatter.parse(dateString) as Date

    }

}