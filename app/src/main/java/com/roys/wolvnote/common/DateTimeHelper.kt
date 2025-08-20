package com.roys.wolvnote.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateTimeHelper {

    fun getCurrentDateTime(): String{
        val dateFormat = SimpleDateFormat("dd MMMM yyyy hh:mm", Locale.getDefault())
        return dateFormat.format(Date())
    }

    fun getTimeZone(): String{
        val timeZone: TimeZone = TimeZone.getDefault()
        return timeZone.id
    }
}