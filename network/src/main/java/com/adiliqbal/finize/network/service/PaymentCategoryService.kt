package com.adiliqbal.finize.network.service

import com.adiliqbal.finize.network.model.ApiPaymentCategory

interface PaymentCategoryService {
    suspend fun getPaymentCategories(): List<ApiPaymentCategory>
}
