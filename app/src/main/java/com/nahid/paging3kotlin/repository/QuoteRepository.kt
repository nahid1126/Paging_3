package com.nahid.paging3kotlin.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.nahid.paging3kotlin.network.ApiService
import com.nahid.paging3kotlin.paging.QuotesPagingSource

class QuoteRepository(private val apiService: ApiService) {

    fun getQuotes() = Pager(config = PagingConfig(pageSize = 20, maxSize = 100),
        pagingSourceFactory = { QuotesPagingSource(apiService) }).liveData
}