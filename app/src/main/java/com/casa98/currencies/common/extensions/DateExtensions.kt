package com.casa98.currencies.common.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toPresentationDate() : String {
    return try {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        format.timeZone = TimeZone.getTimeZone("UTC")

        val todayDate = format.parse(SimpleDateFormat("yyyy-MM-dd'T'00:00:00'Z'", Locale.getDefault()).format(Date()))
        val twittDate = format.parse(this)
        val date = if(twittDate!!.before(todayDate)) {
            SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(twittDate)
        } else {
            SimpleDateFormat("h:mm a", Locale.getDefault()).format(twittDate)
        }
        date
    } catch (ex: Exception) {
        this
    }
}