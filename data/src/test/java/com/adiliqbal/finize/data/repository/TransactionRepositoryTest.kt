package com.adiliqbal.finize.data.repository

import com.adiliqbal.finize.common.system.Logger
import com.adiliqbal.finize.data.conversion.toApi
import com.adiliqbal.finize.data.conversion.toEntity
import com.adiliqbal.finize.database.dao.TransactionDao
import com.adiliqbal.finize.database.model.fake.FakeEntity
import com.adiliqbal.finize.model.fake.FakeModel
import com.adiliqbal.finize.network.service.TransactionService
import com.adiliqbal.finize.network.service.TransactionTemplateService
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.any
import org.mockito.kotlin.times

class TransactionRepositoryTest {

    private val dao = Mockito.mock(TransactionDao::class.java)
    private val service = Mockito.mock(TransactionService::class.java)
    private val templateService = Mockito.mock(TransactionTemplateService::class.java)
    private val repository =
        TransactionRepositoryImpl(dao, service, templateService, Mockito.mock(Logger::class.java))

    @Test
    fun getTransaction() = runTest {
        Mockito.`when`(dao.get("one")).thenReturn(flowOf(FakeEntity.transaction("one")))

        val transaction = repository.getTransaction("one").take(1).firstOrNull()
        assertEquals("one", transaction?.id)
    }

    @Test
    fun createTransaction() = runTest {
        val model = FakeModel.transaction("")
        val api = model.toApi()

        Mockito.`when`(service.createTransaction(api)).thenReturn(api.copy("id"))

        val transaction = repository.createTransaction(model)
        assertEquals("id", transaction.id)
        Mockito.verify(dao, times(1)).upsert(api.copy("id").toEntity())
    }

    @Test
    fun updateTransaction() = runTest {
        val transaction = FakeModel.transaction().copy(name = "new name")

        repository.updateTransaction(transaction)
        Mockito.verify(service, times(1)).updateTransaction(transaction.toApi())
        Mockito.verify(dao, times(1)).upsert(transaction.toApi().toEntity())
    }

    @Test
    fun deleteTransaction() = runTest {
        repository.deleteTransaction("id")
        Mockito.verify(service, times(1)).deleteTransaction("id")
        Mockito.verify(dao, times(1)).delete("id")
    }

    @Test
    fun getTemplates() = runTest {
        Mockito.`when`(dao.getTemplates()).thenReturn(
            flowOf(
                listOf(
                    FakeEntity.transaction("one").copy(isTemplate = true),
                    FakeEntity.transaction("two").copy(isTemplate = true),
                    FakeEntity.transaction("three"),
                    FakeEntity.transaction("four")
                )
            )
        )

        val apiList = listOf(
            FakeModel.transaction("five").toApi(),
            FakeModel.transaction("six").toApi(),
        )
        Mockito.`when`(templateService.getTemplates()).thenReturn(apiList)

        val templates = repository.getTemplates().take(1).lastOrNull()
        assertEquals(2, templates?.size)
        Mockito.verify(dao, times(1)).clearTemplates()
        Mockito.verify(dao, times(1)).upsert(apiList.map { it.toEntity() })
    }

    @Test
    fun getTemplate() = runTest {
        Mockito.`when`(dao.get("one"))
            .thenReturn(flowOf(FakeEntity.transaction("one").copy(isTemplate = true)))

        val transaction = repository.getTemplate("one").take(1).lastOrNull()
        assertEquals("one", transaction?.id)
    }

    @Test
    fun createTemplate() = runTest {
        val model = FakeModel.transaction("")
        val api = FakeModel.transaction("").toApi()

        Mockito.`when`(templateService.createTemplate(any())).thenReturn(api.copy("id"))

        val transaction = repository.createTemplate(model)
        assertEquals("id", transaction.id)
        Mockito.verify(dao, times(1)).upsert(api.copy("id").toEntity().copy(isTemplate = true))
    }

    @Test
    fun updateTemplate() = runTest {
        val transaction = FakeModel.transaction().copy(name = "new name")

        repository.updateTemplate(transaction)
        Mockito.verify(templateService, times(1)).updateTemplate(transaction.toApi())
        Mockito.verify(dao, times(1)).upsert(transaction.toApi().toEntity().copy(isTemplate = true))
    }

    @Test
    fun deleteTemplate() = runTest {
        repository.deleteTemplate("id")
        Mockito.verify(templateService, times(1)).deleteTemplate("id")
        Mockito.verify(dao, times(1)).delete("id")
    }
}
