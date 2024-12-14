package com.gotneb.bookpedia.book.domain

import com.gotneb.bookpedia.core.domain.DataError
import com.gotneb.bookpedia.core.domain.Result

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
}