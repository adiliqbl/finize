package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.NotionBudgetSerializer
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

interface BaseApiBudget {
	val id: ID
	val name: String
	val limit: Double
	val expireAt: LocalDate?
	val createdAt: Instant
}

@Serializable
data class ApiBudget(
	override val id: ID,
	override val name: String,
	override val limit: Double = 0.0,
	override val expireAt: LocalDate? = null,
	override val createdAt: Instant = DateUtil.now()
) : BaseApiBudget

@Serializable(with = NotionBudgetSerializer::class)
internal class NotionApiBudget(budget: ApiBudget) : BaseApiBudget by budget