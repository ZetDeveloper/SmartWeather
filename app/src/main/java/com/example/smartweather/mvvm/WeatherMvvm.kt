package com.example.smartweather.mvvm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartweather.models.RootService
import com.example.smartweather.rest.Network
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherMvvm: ViewModel() {

    private var _data: MutableStateFlow<RootService?> = MutableStateFlow(null)
    val data: StateFlow<RootService?> = _data

    private var _errorData:  MutableStateFlow<Boolean>  = MutableStateFlow(false)
    val errorData: StateFlow<Boolean> = _errorData


    fun getData() {
        viewModelScope.launch {
            val call = Network.service.getForecast("London,uk","8bb387911d1f4c1d2545bfe420f0f3b0")
            val weather = call.body()
            if(call.isSuccessful){
                _errorData.update { false }
                _data.update { weather }
            }else{
                _errorData.update { true }
            }
        }
    }

}
