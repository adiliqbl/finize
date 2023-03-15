package com.adiliqbal.finize.common.util

import java.util.*

object Currencies {

    val default: Currency
        get() = Currency.getInstance(Locale.getDefault())

    fun of(code: String): Currency = Currency.getInstance(code)
}
