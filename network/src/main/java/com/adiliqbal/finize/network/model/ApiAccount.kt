package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.common.util.DateUtil
import com.adiliqbal.finize.model.enums.AccountType
import com.adiliqbal.finize.model.extensions.ID
import com.adiliqbal.finize.network.model.serializer.NotionAccountSerializer
import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

interface BaseApiAccount {
    val id: ID
    val name: String
    val balance: Double
    val type: AccountType
    val budget: ID?
    val currency: String
    val createdAt: Instant
}

@Serializable
data class ApiAccount(
    override val id: ID,
    override val name: String,
    override val type: AccountType,
    override val balance: Double = 0.0,
    override val currency: String,
    override val budget: ID? = null,
    override val createdAt: Instant = DateUtil.now()
) : BaseApiAccount

@Serializable(with = NotionAccountSerializer::class)
internal class NotionApiAccount(account: ApiAccount) : BaseApiAccount by account
