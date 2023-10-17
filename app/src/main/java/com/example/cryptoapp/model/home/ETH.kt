package com.example.cryptoapp.model.home


import com.google.gson.annotations.SerializedName

data class ETH(
    @SerializedName("fully_diluted_market_cap")
    val fullyDilutedMarketCap: Double?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("market_cap")
    val marketCap: Int?,
    @SerializedName("market_cap_dominance")
    val marketCapDominance: Int?,
    @SerializedName("percent_change_1h")
    val percentChange1h: Int?,
    @SerializedName("percent_change_24h")
    val percentChange24h: Int?,
    @SerializedName("percent_change_7d")
    val percentChange7d: Int?,
    @SerializedName("price")
    val price: Int?,
    @SerializedName("volume_24h")
    val volume24h: Int?,
    @SerializedName("volume_change_24h")
    val volumeChange24h: Double?
)