package com.example.cryptoapp.model.home


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("date_added")
    val dateAdded: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("last_updated")
    val lastUpdated: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("quote")
    val quote: Quote?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("tags")
    val tags: List<String>?
)