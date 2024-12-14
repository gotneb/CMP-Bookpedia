package com.gotneb.bookpedia.book.data.network

import com.gotneb.bookpedia.book.domain.Book
import com.gotneb.bookpedia.core.domain.DataError
import com.gotneb.bookpedia.core.domain.Result
import io.ktor.client.HttpClient

class KtorRemoteBookDataSource(
    private val httpClient: HttpClient
) {
    suspend fun searchBooks(
        query: String,
        resultLimit: Int? = null
    ): Result<List<Book>, DataError.Remote> {
        TODO()
    }
}