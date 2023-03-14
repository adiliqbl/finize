package com.adiliqbal.finize.network.model.request

import com.adiliqbal.finize.network.model.serializer.NotionDatabaseQuerySerializer
import kotlinx.serialization.Serializable

@Serializable(with = NotionDatabaseQuerySerializer::class)
internal class NotionDatabaseQuery(query: PaginationQuery) : BasePaginationQuery by query

internal object NotionDatabaseKeys {
    const val SORT = "sorts"
    const val SORT_FIELD = "property"
    const val SORT_ORDER = "direction"
    const val FILTER = "filter"
    const val CURSOR = "start_cursor"
    const val PAGE_SIZE = "page_size"
}
