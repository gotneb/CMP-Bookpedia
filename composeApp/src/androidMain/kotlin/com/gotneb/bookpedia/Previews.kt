package com.gotneb.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gotneb.bookpedia.book.domain.Book
import com.gotneb.bookpedia.book.presentation.book_list.BookListScreen
import com.gotneb.bookpedia.book.presentation.book_list.BookListState
import com.gotneb.bookpedia.book.presentation.components.BookList
import com.gotneb.bookpedia.book.presentation.components.BookSearchBar

@Preview(showBackground = true)
@Composable
fun Previews() {
    MaterialTheme {
        BookSearchBar(
            searchQuery = "",
            onImeSearch = {},
            onSearchQueryChange = {},
        )
    }
}

private val books = (1..100).map {
    Book(
        id = it.toString(),
        title = "Book $it",
        imageUrl = "https://test.com",
        authors = listOf("Gabriel Bento"),
        description = "Description $it",
        languages = emptyList(),
        firstPublishYear = null,
        averageRating = 4.57241,
        ratingCount = 12,
        numPages = 100,
        numEditions = 3,
    )
}

@Preview
@Composable
private fun BookListScreenPreview() {
    BookListScreen(
        state = BookListState(
            searchResults = books
        ),
        onAction = {},
    )
}