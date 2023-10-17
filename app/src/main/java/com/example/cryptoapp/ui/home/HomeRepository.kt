package com.example.cryptoapp.ui.home

import com.example.cryptoapp.base.BaseRepository
import com.example.cryptoapp.network.ApiFactory
import java.nio.channels.spi.AbstractSelectionKey
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apiFactory : ApiFactory) : BaseRepository() {

    suspend fun getData(
        apiKey: String,
        limit: String
    ) = safeApiRequest {
        apiFactory.getData(apiKey,limit)
    }


}