package com.gotneb.bookpedia.book.data.network

import com.gotneb.bookpedia.book.data.dto.SearchResponseDto
import com.gotneb.bookpedia.core.domain.DataError
import com.gotneb.bookpedia.core.domain.Result

interface RemoteBookDataSource {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<SearchResponseDto, DataError.Remote>
}