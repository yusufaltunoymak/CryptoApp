package com.example.cryptoapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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
        limit : Int,
    ) = viewModelScope.launch {
        isLoading.value = true
        val request = repository.getData(apiKey,limit)
        when(request) {
            is NetworkResult.Success -> {
                cryptoResponse.value = request.data
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                onError.value = request.message
                isLoading.value = false
            }
        }

    }
    val cryptoModel = repository.cryptoModel.cachedIn(viewModelScope)


}
