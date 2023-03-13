package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.common.util.CurrencyUtil
import com.adiliqbal.finize.database.model.MoneyEntity
import com.adiliqbal.finize.model.Money
import com.adiliqbal.finize.network.model.ApiMoney

internal fun Money.toApi() = ApiMoney(amount, currency.currencyCode)

internal fun ApiMoney.toEntity() = MoneyEntity(amount, currency)

internal fun MoneyEntity.toModel() = Money(amount, CurrencyUtil.of(currency))