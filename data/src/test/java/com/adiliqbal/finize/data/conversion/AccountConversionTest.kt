package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.model.fake.FakeModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class AccountConversionTest {

    @Test
    fun modelToApi() {
        val model = FakeModel.account()
        val api = model.toApi()

        assertEquals(model.id, api.id)
        assertEquals(model.name, api.name)
        assertEquals(model.type, api.type)
        assertEquals(model.balance, api.balance)
        assertEquals(model.currency.currencyCode, api.currency)
    }

    @Test
    fun apiToEntity() {
        val api = FakeModel.account().toApi()
        val entity = api.toEntity()

        assertEquals(api.id, entity.id)
        assertEquals(api.name, entity.name)
        assertEquals(api.type, entity.type)
        assertEquals(api.balance, entity.balance)
        assertEquals(api.currency, entity.currency)
    }

    @Test
    fun entityToModel() {
        val entity = FakeModel.account().toApi().toEntity()
        val model = entity.toModel()

        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.type, model.type)
        assertEquals(entity.balance, model.balance)
        assertEquals(entity.currency, model.currency.currencyCode)
    }
}
