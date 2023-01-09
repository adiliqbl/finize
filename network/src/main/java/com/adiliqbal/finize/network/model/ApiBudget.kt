package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

interface BaseApiBudget {
	val id: ID
	val name: String
	val spent: Double
	val maximum: Double
	val expireDate: LocalDate?
	val createdAt: Instant
}

@Serializable
data class ApiBudget(
	override val id: ID,
	override val name: String,
	override val spent: Double = 0.0,
	override val maximum: Double = 0.0,
	override val expireDate: LocalDate? = null,
	override val createdAt: Instant = DateUtil.now()
) : BaseApiBudget