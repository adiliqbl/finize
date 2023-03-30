package com.adiliqbal.finize.database.converters

import androidx.room.TypeConverter
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

internal class DateConverters {

    @TypeConverter
    fun toInstant(value: Long?): Instant? = value?.let(Instant::fromEpochMilliseconds)

    @TypeConverter
    fun fromInstant(instant: Instant?): Long? = instant?.toEpochMilliseconds()

    @TypeConverter
    fun toLocalDate(value: Int?): LocalDate? = value?.let(LocalDate::fromEpochDays)

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): Int? = date?.toEpochDays()
}
