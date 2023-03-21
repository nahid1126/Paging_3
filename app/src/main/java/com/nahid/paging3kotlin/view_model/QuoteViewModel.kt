package com.nahid.paging3kotlin.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nahid.paging3kotlin.network.ApiClient
import com.nahid.paging3kotlin.network.ApiService
import com.nahid.paging3kotlin.network.BaseUrl
import com.nahid.paging3kotlin.repository.QuoteRepository

class QuoteViewModel : ViewModel() {
    private val quoteRepository:QuoteRepository

    init {
        val apiService = ApiClient.getApiClient(BaseUrl.baseUrl)!!.create(ApiService::class.java)
        quoteRepository = QuoteRepository(apiService)
    }

    val quoteList = quoteRepository.getQuotes().cachedIn(viewModelScope)
}