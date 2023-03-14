package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.enums.TaskFrequency
import com.adiliqbal.finize.model.enums.TaskType
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone

data class RecurringTask(
    val id: ID,
    val user: ID,
    val type: TaskType,
    val frequency: TaskFrequency,
    val recurringTime: Int,
    val timezone: TimeZone,
    val data: String,
    val lastDate: Instant,
    val createdAt: Instant
)
