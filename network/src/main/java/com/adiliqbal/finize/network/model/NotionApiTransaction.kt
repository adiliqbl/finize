package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.network.model.serializer.NotionTransactionSerializer
import kotlinx.serialization.Serializable

@Serializable(with = NotionTransactionSerializer::class)
internal class NotionApiTransaction(transaction: ApiTransaction) : BaseApiTransaction by transaction