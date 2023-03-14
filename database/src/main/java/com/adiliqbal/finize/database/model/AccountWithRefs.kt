package com.adiliqbal.finize.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class AccountWithRefs(
    @Embedded val account: AccountEntity,
    @Relation(parentColumn = "budget", entityColumn = "id") val budget: BudgetEntity? = null,
)
