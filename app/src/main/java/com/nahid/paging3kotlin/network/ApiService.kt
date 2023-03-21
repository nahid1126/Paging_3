package com.nahid.paging3kotlin.network

import com.nahid.paging3kotlin.model.ModelQuotesList
import retrofit2.http.*
import retrofit2.Response

interface ApiService {
    @GET("quotes")
    suspend fun getQuotes(@Query("page")page:Int): ModelQuotesList

}