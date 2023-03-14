package com.adiliqbal.finize.model

import com.adiliqbal.finize.model.enums.AccountType
import com.adiliqbal.finize.model.extensions.ID
import java.util.*
import kotlinx.datetime.Instant

data class Account(
    val id: ID = "",
    val name: String,
    val balance: Double = 0.0,
    val type: AccountType,
    val currency: Currency,
    val budget: Budget? = null,
    val createdAt: Instant
)
