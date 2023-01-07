package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

interface BaseApiBudget {
	val id: ID
	val name: String
	val spent: Double
	val maximum: Double
	val createdAt: Instant
}

@Serializable
data class ApiBudget(
	override val id: ID,
	override val name: String,
	override val spent: Double = 0.0,
	override val maximum: Double = 0.0,
	override val createdAt: Instant = DateUtil.currentTime()
) : BaseApiBudget