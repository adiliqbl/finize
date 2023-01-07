package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.TransactionTypeSerializer
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

interface BaseApiTransaction {
	val id: ID
	val name: String
	val type: TransactionType
	val toAccount: ID?
	val fromAccount: ID?
	val budget: ID?
	val tags: List<String>?
	val note: String?
	val date: LocalDate
}

@Serializable
data class ApiTransaction(
	override val id: ID,
	override val name: String,
	@Serializable(TransactionTypeSerializer::class)
	override val type: TransactionType = TransactionType.UNKNOWN,
	override val toAccount: ID? = null,
	override val fromAccount: ID? = null,
	override val budget: ID? = null,
	override val tags: List<String>? = null,
	override val note: String? = null,
	override val date: LocalDate = DateUtil.currentDay(),
) : BaseApiTransaction