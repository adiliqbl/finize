package com.adiliqbal.finize.database.converters

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

class DateConverters {

	@TypeConverter
	fun toInstant(value: Long?): Instant? = value?.let(Instant::fromEpochMilliseconds)

	@TypeConverter
	fun fromInstant(instant: Instant?): Long? = instant?.toEpochMilliseconds()
}
