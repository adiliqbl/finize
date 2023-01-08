package com.adiliqbal.finize.common.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

object DateUtil {

	fun today() = with(java.time.LocalDate.now()) {
		LocalDate(year = year, month = month, dayOfMonth = dayOfMonth)
	}

	fun now() = Clock.System.now()

	fun currentTimeMillis() = now().toEpochMilliseconds()

	fun parseDate(value: String) = try {
		LocalDate.parse(value).instant
	} catch (_: Exception) {
		Instant.parse(value)
	}
}

val Instant.date get() = toLocalDateTime(TimeZone.UTC).date

val LocalDate.instant get() = atStartOfDayIn(TimeZone.UTC)