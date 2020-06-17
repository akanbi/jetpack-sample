package com.akanbi.jetpacksample.infrastructure.database.converters

import androidx.room.TypeConverter
import org.joda.time.LocalDateTime
import java.util.*

class DateConverter {

    @TypeConverter
    fun convertTimestampToDate(timestamp: Long?): LocalDateTime? {
        return timestamp?.let { LocalDateTime(timestamp) }
    }

    @TypeConverter
    fun convertDateToTimestamp(date: LocalDateTime?): Long? {
        return date?.toDate()?.time
    }

}