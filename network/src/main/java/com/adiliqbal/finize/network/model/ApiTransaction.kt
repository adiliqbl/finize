package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable(with = NotionTransactionSerializer::class)
data class ApiTransaction(
	val id: ID,
	val name: String,
	val type: ApiTag? = null,
	val toAccount: ID? = null,
	val fromAccount: ID? = null,
	val budgets: List<ID>? = null,
	val tags: List<ApiTag>? = null,
	val note: String? = null,
	val date: LocalDate = DateUtil.currentDay(),
)