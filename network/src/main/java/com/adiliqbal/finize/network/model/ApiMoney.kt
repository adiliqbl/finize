package com.adiliqbal.finize.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiMoney(val amount: Double, val currency: String)
