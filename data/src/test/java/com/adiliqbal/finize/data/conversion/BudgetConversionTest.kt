package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.model.fake.FakeModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BudgetConversionTest {

    @Test
    fun modelToApi() {
        val model = FakeModel.budget()
        val api = model.toApi()

        assertEquals(model.id, api.id)
        assertEquals(model.name, api.name)
        assertEquals(model.limit, api.limit)
    }

    @Test
    fun apiToEntity() {
        val api = FakeModel.budget().toApi()
        val entity = api.toEntity()

        assertEquals(api.id, entity.id)
        assertEquals(api.name, entity.name)
        assertEquals(api.limit, entity.limit)
    }

    @Test
    fun entityToModel() {
        val entity = FakeModel.budget().toApi().toEntity()
        val model = entity.toModel()

        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.limit, model.limit)
    }
}
