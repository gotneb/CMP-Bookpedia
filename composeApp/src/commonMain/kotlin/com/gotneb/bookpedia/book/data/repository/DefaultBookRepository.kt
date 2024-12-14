package com.gotneb.bookpedia.book.data.repository

import com.gotneb.bookpedia.book.data.mappers.toBook
import com.gotneb.bookpedia.book.data.network.RemoteBookDataSource
import com.gotneb.bookpedia.book.domain.Book
import com.gotneb.bookpedia.book.domain.BookRepository
import com.gotneb.bookpedia.core.domain.DataError
import com.gotneb.bookpedia.core.domain.Result
import com.gotneb.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDataSource: RemoteBookDataSource
): BookRepository {
    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDataSource.searchBooks(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }
}