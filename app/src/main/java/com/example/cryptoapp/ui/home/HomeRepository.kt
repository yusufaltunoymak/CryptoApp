package com.example.cryptoapp.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.cryptoapp.base.BaseRepository
import com.example.cryptoapp.network.ApiFactory
import com.example.cryptoapp.pagination.CryptoPagingSource
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiFactory : ApiFactory) : BaseRepository() {

    val cryptoModel = Pager(PagingConfig(1)) {
        CryptoPagingSource(apiFactory)
    }.liveData

    suspend fun getData(
        apiKey: String,
        limit: Int
    ) = safeApiRequest {
        apiFactory.getData(apiKey, limit, 1)
    }



}