package com.adiliqbal.finize.common.util

import kotlinx.datetime.LocalDate

object DateUtil {

	fun currentDay() = with(java.time.LocalDate.now()) {
		LocalDate(year = year, month = month, dayOfMonth = dayOfMonth)
	}
}