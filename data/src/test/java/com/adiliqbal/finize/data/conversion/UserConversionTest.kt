package com.adiliqbal.finize.data.conversion

import com.adiliqbal.finize.model.fake.FakeModel
import junit.framework.TestCase.assertEquals
import org.junit.Test

class UserConversionTest {

    @Test
    fun modelToApi() {
        val model = FakeModel.user()
        val api = model.toApi()

        assertEquals(model.id, api.id)
        assertEquals(model.name, api.name)
        assertEquals(model.email, api.email)
        assertEquals(model.image, api.image)
        assertEquals(model.profile, api.profile?.toEntity()?.toModel())
    }

    @Test
    fun apiToEntity() {
        val api = FakeModel.user().toApi()
        val entity = api.toEntity()

        assertEquals(api.id, entity.id)
        assertEquals(api.name, entity.name)
        assertEquals(api.email, entity.email)
        assertEquals(api.image, entity.image)
        assertEquals(api.profile, entity.profile.toModel().toApi())
    }

    @Test
    fun entityToModel() {
        val entity = FakeModel.user().toApi().toEntity()
        val model = entity.toModel()

        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.email, model.email)
        assertEquals(entity.image, model.image)
        assertEquals(entity.profile, model.profile.toApi().toEntity())
    }
}
