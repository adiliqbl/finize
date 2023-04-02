package com.adiliqbal.finize.common.util

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.ZoneOffset
import java.time.ZonedDateTime
import kotlin.math.abs

class DateUtilTest {

	@Test
	fun today() {
		val expected = Instant.fromEpochMilliseconds(
			ZonedDateTime.now(ZoneOffset.UTC).toInstant().toEpochMilli()
		).toLocalDateTime(TimeZone.UTC).date

		assertEquals(expected, DateUtil.today())
	}

	@Test
	fun currentTime() {
		val expected = Instant.fromEpochMilliseconds(
			ZonedDateTime.now(ZoneOffset.UTC).toInstant().toEpochMilli()
		)
		assertTrue(
			abs(
				expected.toEpochMilliseconds().minus(DateUtil.now().toEpochMilliseconds())
			) <= 50
		)
	}

	@Test
	fun `currentTime in millis`() {
		assertTrue(abs(System.currentTimeMillis().minus(DateUtil.currentTimeMillis())) <= 50)
	}

	@Test
	fun `parseDate can parse an ISO date string`() {
		val value = "2022-02-22T10:30:00Z"
		val result = DateUtil.parseDate(value)
		assertEquals(Instant.parse(value), result)
	}

	@Test
	fun `parseDate can parse a non-ISO date string`() {
		val expected = Instant.fromEpochMilliseconds(
			ZonedDateTime.of(
				2022, 2, 22, 0, 0, 0, 0,
				ZoneOffset.UTC
			).toInstant().toEpochMilli()
		)

		assertEquals(expected, DateUtil.parseDate("2022-02-22"))
	}

	@Test
	fun `parseDate returns null for an invalid date string`() {
		assertEquals(null, DateUtil.parseDate("invalid-date-string"))
	}

	@Test
	fun `instant to date only`() {
		val instant = Instant.fromEpochMilliseconds(1648976400000) // 2022-02-01T10:20:00Z
		val expected = instant.toLocalDateTime(TimeZone.UTC).date
		assertEquals(expected, instant.date)
	}

	@Test
	fun `local date to instant`() {
		val localDate = LocalDate(2022, 2, 1)
		assertEquals(localDate.atStartOfDayIn(TimeZone.UTC), localDate.instant)
	}
}
