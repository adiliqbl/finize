package com.adiliqbal.finize.model.enums

enum class TaskFrequency(val value: String) {
    WEEKLY("weekly"),
    MONTHLY("monthly"),
    YEARLY("yearly")
}

fun String.toTaskFrequency() = TaskFrequency.values().find { it.value === this }
