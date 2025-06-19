package com.defenseunicorns.flyaware.ui

import androidx.lifecycle.ViewModel
import com.defenseunicorns.flyaware.data.network.AviationWeatherApi
import com.defenseunicorns.flyaware.data.local.IcaoDao
import com.defenseunicorns.flyaware.data.local.IcaoEntity
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlyAwareViewModel @Inject constructor(
    private val weatherApi: AviationWeatherApi,
    private val icaoDao: IcaoDao
) : ViewModel() {
    // weatherApi.getMetar("KPHX")
    // weatherApi.getTaf("KPHX")

    fun insertIcao(icao: String) {
        viewModelScope.launch {
            if (icao.length == 4) {
                icaoDao.insert(IcaoEntity(icao.uppercase()))
            }
        }
    }
}