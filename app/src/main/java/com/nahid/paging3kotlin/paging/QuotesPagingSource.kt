package com.nahid.paging3kotlin.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nahid.paging3kotlin.network.ApiService
import com.nahid.paging3kotlin.model.Result

class QuotesPagingSource(private val apiService: ApiService) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        //anchorPosition means recently accessed page index
        //closestPageToPosition means i pass the position and this give me the closest page
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }

        /*if (state.anchorPosition != null) {
            //get anchorPage
            val anchorPage = state.closestPageToPosition(state.anchorPosition!!)
            return if (anchorPage?.prevKey != null){
                anchorPage.prevKey!!.plus(1)
            } else if (anchorPage?.nextKey != null){
                anchorPage.nextKey!!.minus(1)
            }else{
                null
            }
        }*/
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            //which page load
            val position = params.key ?: 1
            val response = apiService.getQuotes(position)

            //passing load data, previousKey and nextKey
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}