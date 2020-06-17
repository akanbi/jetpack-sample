package com.akanbi.jetpacksample.infrastructure

import com.akanbi.jetpacksample.infrastructure.database.converters.DateConverter
import org.joda.time.LocalDateTime
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateConverterTest {

    private val converter by lazy { DateConverter() }

    @Test
    fun shouldConvertTimestampToDate() {
        val timestamp = Date().time
        val localDateTime = converter.convertTimestampToDate(timestamp)

        assertEquals(timestamp, localDateTime?.toDate()?.time)
    }

    @Test
    fun shouldConvertDateToTimestamp() {
        val localDateTime = LocalDateTime.now()
        val timestamp = localDateTime.toDate().time

        assertEquals(timestamp, converter.convertDateToTimestamp(localDateTime))
    }
}
