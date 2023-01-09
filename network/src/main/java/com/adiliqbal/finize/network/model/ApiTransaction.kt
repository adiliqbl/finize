package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

interface BaseApiTransaction {
	val id: ID
	val name: String
	val category: List<String>
	val toAccount: ID?
	val fromAccount: ID?
	val budget: ID?
	val currency: String
	val note: String?
	val date: Instant
}

@Serializable
data class ApiTransaction(
	override val id: ID,
	@SerialName(NAME) override val name: String,
	@SerialName(CATEGORY) override val category: List<String>,
	@SerialName(TO_ACCOUNT) override val toAccount: ID? = null,
	@SerialName(FROM_ACCOUNT) override val fromAccount: ID? = null,
	@SerialName(BUDGET) override val budget: ID? = null,
	@SerialName(CURRENCY) override val currency: String,
	@SerialName(NOTE) override val note: String? = null,
	@SerialName(DATE) override val date: Instant = DateUtil.now(),
) : BaseApiTransaction {

	internal companion object Keys {
		internal const val NAME = "name"
		internal const val KEYWORDS = "keywords"
		internal const val CATEGORY = "category"
		internal const val TO_ACCOUNT = "toAccount"
		internal const val FROM_ACCOUNT = "fromAccount"
		internal const val BUDGET = "budget"
		private const val CURRENCY = "currency"
		private const val NOTE = "note"
		internal const val DATE = "date"
	}
}