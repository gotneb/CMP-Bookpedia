package com.gotneb.bookpedia

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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