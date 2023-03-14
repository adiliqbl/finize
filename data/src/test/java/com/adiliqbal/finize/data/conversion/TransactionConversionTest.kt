package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.model.fake.FakeModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TransactionConversionTest {

    @Test
    fun modelToApi() {
        val model = FakeModel.transaction()
        val api = model.toApi()

        assertEquals(model.id, api.id)
        assertEquals(model.name, api.name)
        assertEquals(model.amount, api.amount.toEntity().toModel())
        assertEquals(model.amountTo, api.amountTo?.toEntity()?.toModel())
        assertEquals(model.amountFrom, api.amountFrom?.toEntity()?.toModel())
        assertEquals(model.amountLocal, api.amountLocal?.toEntity()?.toModel())
        assertEquals(model.accountTo, api.accountTo)
        assertEquals(model.accountFrom, api.accountFrom)
        assertEquals(model.budget, api.budget)
        assertEquals(model.categories, api.categories)
        assertEquals(model.date, api.date)
    }

    @Test
    fun apiToEntity() {
        val api = FakeModel.transaction().toApi()
        val entity = api.toEntity()

        assertEquals(api.id, entity.id)
        assertEquals(api.name, entity.name)
        assertEquals(api.amount, entity.amount.toModel().toApi())
        assertEquals(api.amountTo, entity.amountTo?.toModel()?.toApi())
        assertEquals(api.amountFrom, entity.amountFrom?.toModel()?.toApi())
        assertEquals(api.amountLocal, entity.amountLocal?.toModel()?.toApi())
        assertEquals(api.accountTo, entity.accountTo)
        assertEquals(api.accountFrom, entity.accountFrom)
        assertEquals(api.budget, entity.budget)
        assertEquals(api.categories, entity.categories)
        assertEquals(api.date, entity.date)
    }

    @Test
    fun entityToModel() {
        val entity = FakeModel.transaction().toApi().toEntity()
        val model = entity.toModel()

        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.amount, model.amount.toApi().toEntity())
        assertEquals(entity.amountTo, model.amountTo?.toApi()?.toEntity())
        assertEquals(entity.amountFrom, model.amountFrom?.toApi()?.toEntity())
        assertEquals(entity.amountLocal, model.amountLocal?.toApi()?.toEntity())
        assertEquals(entity.accountTo, model.accountTo)
        assertEquals(entity.accountFrom, model.accountFrom)
        assertEquals(entity.budget, model.budget)
        assertEquals(entity.categories, model.categories)
        assertEquals(entity.date, model.date)
    }
}
