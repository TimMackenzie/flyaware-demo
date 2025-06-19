package com.defenseunicorns.flyaware.ui

import androidx.lifecycle.ViewModel
import com.defenseunicorns.flyaware.data.network.AviationWeatherApi
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FlyAwareViewModel @Inject constructor(
    private val weatherApi: AviationWeatherApi
) : ViewModel() {
    // weatherApi.getMetar("KPHX")
    // weatherApi.getTaf("KPHX")
}