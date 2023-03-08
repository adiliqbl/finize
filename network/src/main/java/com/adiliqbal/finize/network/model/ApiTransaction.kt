package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface BaseApiTransaction {
	val id: ID
	val name: String
	val amount: ApiMoney
	val amountTo: ApiMoney?
	val amountFrom: ApiMoney?
	val amountLocal: ApiMoney?
	val accountTo: ID?
	val accountFrom: ID?
	val budget: ID?
	val task: ID?
	val note: String?
	val categories: List<String>
	val date: Instant
	val isTemplate: Boolean
}

@Serializable
data class ApiTransaction(
	override val id: ID,
	@SerialName(NAME) override val name: String,
	@SerialName(AMOUNT) override val amount: ApiMoney,
	@SerialName(AMOUNT_TO) override val amountTo: ApiMoney?,
	@SerialName(AMOUNT_FROM) override val amountFrom: ApiMoney?,
	@SerialName(AMOUNT_LOCAL) override val amountLocal: ApiMoney?,
	@SerialName(ACCOUNT_TO) override val accountTo: ID? = null,
	@SerialName(ACCOUNT_FROM) override val accountFrom: ID? = null,
	@SerialName(BUDGET) override val budget: ID? = null,
	@SerialName(TASK) override val task: ID? = null,
	@SerialName(NOTE) override val note: String? = null,
	@SerialName(CATEGORIES) override val categories: List<String>,
	@SerialName(DATE) override val date: Instant = DateUtil.now(),
	override val isTemplate: Boolean = false
) : BaseApiTransaction {

	internal companion object Keys {
		internal const val NAME = "name"
		internal const val KEYWORDS = "keywords"
		internal const val AMOUNT = "amount"
		internal const val AMOUNT_TO = "amountTo"
		internal const val AMOUNT_FROM = "amountFrom"
		internal const val AMOUNT_LOCAL = "amountLocal"
		internal const val ACCOUNT_TO = "accountTo"
		internal const val ACCOUNT_FROM = "accountFrom"
		internal const val BUDGET = "budget"
		private const val TASK = "task"
		private const val NOTE = "note"
		internal const val CATEGORIES = "categories"
		internal const val DATE = "date"
	}
}

@Serializable(with = NotionTransactionSerializer::class)
internal class NotionApiTransaction(transaction: ApiTransaction) : BaseApiTransaction by transaction