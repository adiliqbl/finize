package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.network.model.serializer.NotionAccountSerializer
import kotlinx.serialization.Serializable

@Serializable(with = NotionAccountSerializer::class)
internal class NotionApiAccount(account: ApiAccount) : BaseApiAccount by account