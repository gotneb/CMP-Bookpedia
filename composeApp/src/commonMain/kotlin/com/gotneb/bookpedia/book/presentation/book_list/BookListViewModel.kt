package com.gotneb.bookpedia.book.presentation.book_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gotneb.bookpedia.book.domain.Book
import com.gotneb.bookpedia.book.domain.BookRepository
import com.gotneb.bookpedia.core.domain.onError
import com.gotneb.bookpedia.core.domain.onSuccess
import com.gotneb.bookpedia.core.presentation.UiText
import cpm_bookpedia.composeapp.generated.resources.Res
import cpm_bookpedia.composeapp.generated.resources.error_unknown
import cpm_bookpedia.composeapp.generated.resources.no_search_results
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

/*
 * --------------------------------------------------
 * - Presentation -> Domain <- Data
 * --------------------------------------------------
 * 1. 'Presentation' is allowed to access 'Domain';
 * 2. 'Data' is allowed to access 'Domain';
 * 3. 'Domain' has only access inside of it;
 */

class BookListViewModel(
    private val bookRepository: BookRepository
): ViewModel() {
    private var cachedBooks = emptyList<Book>()

    private val _state = MutableStateFlow(BookListState())
    val state = _state
        .onStart {
            if (cachedBooks.isEmpty()) {
                observeSearchQuery()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value,
        )

    fun onAction(action: BookListAction) {
        when (action) {
            is BookListAction.OnBookClick -> {

            }
            is BookListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query)
                }
            }
            is BookListAction.OnTabSelected -> {
                _state.update {
                    it.copy(selectedTabIndex = action.index)
                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update { it.copy(
                            errorMessage = null,
                            searchResults = cachedBooks,
                        ) }
                    }
                    query.length >= 2 -> {
                        searchBooks(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchBooks(query: String) {
        _state.update { it.copy(
            isLoading = true
        ) }
        viewModelScope.launch {
            bookRepository
                .searchBooks(query)
                .onSuccess { searchResults ->
                    _state.update { it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResults = searchResults,
                    ) }
                }
                .onError {
                    _state.update { it.copy(
                        searchResults = emptyList(),
                        isLoading = false,
                        errorMessage = UiText.StringResourceId(Res.string.error_unknown)
                    ) }
                }
        }
    }
}