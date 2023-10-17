package com.example.cryptoapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.model.home.CryptoResponse
import com.example.cryptoapp.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository : HomeRepository) : ViewModel() {

    val cryptoResponse : MutableLiveData<CryptoResponse?> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(true)
    val onError : MutableLiveData<String?> = MutableLiveData()

    fun getData(
        apiKey : String,
        limit : String
    ) = viewModelScope.launch {
        isLoading.value = true
        val request = repository.getData(apiKey,limit)
        when(request) {
            is NetworkResult.Success -> {
                cryptoResponse.value = request.data
                Log.e("qqqqqqqqqqqqData:",request.data.toString())
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                onError.value = request.message
                Log.e("qqqqqqqqqqqq",request.message.toString())
                isLoading.value = false
            }
        }

    }
    }