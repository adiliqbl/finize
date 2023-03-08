package com.adiliqbal.finize.model.enums

enum class TaskType(val value: String) {
	CREATE_TRANSACTION("create-transaction")
}

fun String.toTaskType() = TaskType.values().find { it.value === this }