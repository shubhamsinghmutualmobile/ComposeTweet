package com.praxis.feat.authentication.ui.search

sealed class SearchState {
    object Loading : SearchState()
    class Success(val searchData: List<SearchTwitter>) : SearchState()
}
