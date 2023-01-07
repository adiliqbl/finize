package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.extensions.ID
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

interface BaseApiAccount {
	val id: ID
	val name: String
	val currentBalance: Double
	val startingBalance: Double
	val createdAt: Instant
}

@Serializable
data class ApiAccount(
	override val id: ID,
	override val name: String,
	override val currentBalance: Double = 0.0,
	override val startingBalance: Double = 0.0,
	override val createdAt: Instant = DateUtil.currentTime()
) : BaseApiAccount