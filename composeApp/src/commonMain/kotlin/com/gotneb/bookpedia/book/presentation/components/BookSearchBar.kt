package com.gotneb.bookpedia.book.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gotneb.bookpedia.core.presentation.DarkBlue
import com.gotneb.bookpedia.core.presentation.SandYellow
import cpm_bookpedia.composeapp.generated.resources.Res
import cpm_bookpedia.composeapp.generated.resources.search_hint
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun BookSearchBar(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onImeSearch: () -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = onSearchQueryChange,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = DarkBlue,
            focusedBorderColor = SandYellow,
        ),
        shape = RoundedCornerShape(100),
        placeholder = { Text(text = stringResource(Res.string.search_hint)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f)
            )
        },
        singleLine = true,
        keyboardActions = KeyboardActions(
            onSearch = { onImeSearch() }
        ),
        trailingIcon = {
            AnimatedVisibility(visible = searchQuery.isNotBlank()) {
                IconButton(onClick = { onSearchQueryChange("") }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(Res.string.search_hint),
                    )
                }
            }
        }
    )
}

@Preview
@Composable
fun BookSearchBarPreview() {
    BookSearchBar(
        searchQuery = "",
        onSearchQueryChange = {},
        onImeSearch = {},
    )
}