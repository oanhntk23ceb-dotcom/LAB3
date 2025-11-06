package com.example.marsphotos.overview

import com.example.marsphotos.network.MarsApi

private fun getMarsPhotos(){
    viewModelScope.launch{
        val listResult = MarsApi.retrofitService.getPhotos()
        _status.value = listResult
    }
}