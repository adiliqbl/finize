package com.adiliqbal.finize.network.model

import com.adiliqbal.finize.network.model.serializer.NotionBudgetSerializer
import kotlinx.serialization.Serializable

@Serializable(with = NotionBudgetSerializer::class)
internal class NotionApiBudget(budget: ApiBudget) : BaseApiBudget by budget