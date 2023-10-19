package com.example.cryptoapp.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.cryptoapp.model.home.Data
import com.example.cryptoapp.network.ApiFactory
import com.example.cryptoapp.util.Constants
import retrofit2.HttpException

class CryptoPagingSource(private val apiFactory: ApiFactory) : PagingSource<Int,com.example.cryptoapp.model.home.Data>() {
    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val currentPage = params.key ?:1
            val offsetForPagination = (currentPage - 1) * 20 + 1
            val response = apiFactory.getData(Constants.API_KEY, params.loadSize,offsetForPagination)
            val cryptoModel = response.data

            LoadResult.Page(
                data = cryptoModel,
                prevKey = if(currentPage == 1) null else currentPage -1,
                nextKey = if(cryptoModel?.isNotEmpty() == true) currentPage +1 else null
            )
        }
        catch (e : Exception) {
            LoadResult.Error(e)
        }
        catch (httpException : HttpException) {
            LoadResult.Error(httpException)
        }
    }
}