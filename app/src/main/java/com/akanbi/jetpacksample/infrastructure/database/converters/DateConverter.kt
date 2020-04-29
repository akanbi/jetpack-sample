package com.akanbi.jetpacksample.infrastructure.database.converters

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun convertTimestampToDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun convertDateToTimestamp(date: Date?): Long? {
        return date?.time
    }

}