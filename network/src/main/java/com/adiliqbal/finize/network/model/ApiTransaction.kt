package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.enums.TransactionType
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.TransactionTypeSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
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
	val date: Instant
}

@Serializable
data class ApiTransaction(
	override val id: ID,
	@SerialName(NAME) override val name: String,
	@Serializable(TransactionTypeSerializer::class)
	@SerialName(TYPE) override val type: TransactionType = TransactionType.UNKNOWN,
	@SerialName(TO_ACCOUNT) override val toAccount: ID? = null,
	@SerialName(FROM_ACCOUNT) override val fromAccount: ID? = null,
	@SerialName(BUDGET) override val budget: ID? = null,
	@SerialName(TAGS) override val tags: List<String>? = null,
	@SerialName(NOTE) override val note: String? = null,
	@SerialName(DATE) override val date: Instant = DateUtil.now(),
) : BaseApiTransaction {

	internal companion object Keys {
		internal const val NAME = "name"
		internal const val KEYWORDS = "keywords"
		internal const val TYPE = "type"
		internal const val TO_ACCOUNT = "toAccount"
		internal const val FROM_ACCOUNT = "fromAccount"
		internal const val BUDGET = "budget"
		internal const val TAGS = "tags"
		private const val NOTE = "note"
		internal const val DATE = "date"
	}
}