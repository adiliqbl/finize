package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.database.model.TransactionEntity
import com.adiliqbal.finize.model.Transaction
import com.adiliqbal.finize.network.model.ApiTransaction

internal fun ApiTransaction.toEntity() = TransactionEntity(
	id = id,
	name = name,
	amount = amount.toEntity(),
	amountTo = amountTo?.toEntity(),
	amountFrom = amountFrom?.toEntity(),
	amountLocal = amountLocal?.toEntity(),
	accountTo = accountTo,
	accountFrom = accountFrom,
	budget = budget,
	task = task,
	note = note,
	categories = categories,
	date = date,
	isTemplate = isTemplate
)

internal fun TransactionEntity.toModel() = Transaction(
	id = id,
	name = name,
	amount = amount.toModel(),
	amountTo = amountTo?.toModel(),
	amountFrom = amountFrom?.toModel(),
	amountLocal = amountLocal?.toModel(),
	accountTo = accountTo,
	accountFrom = accountFrom,
	budget = budget,
	task = task,
	note = note,
	categories = categories,
	date = date
)