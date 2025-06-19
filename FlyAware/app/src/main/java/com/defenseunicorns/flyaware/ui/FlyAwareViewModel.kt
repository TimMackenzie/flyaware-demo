package com.defenseunicorns.flyaware.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.defenseunicorns.flyaware.data.local.IcaoDao
import com.defenseunicorns.flyaware.data.network.AviationWeatherApi
import com.defenseunicorns.flyaware.domain.MetarTransformer
import com.defenseunicorns.flyaware.model.Metar
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FlyAwareViewModel @Inject constructor(
    private val weatherApi: AviationWeatherApi,
    private val icaoDao: IcaoDao,
    private val transformer: MetarTransformer
) : ViewModel() {

    // Current list of all user-selected ICAO codes, updated when changes occur
    val icaoCodes: StateFlow<List<String>> = icaoDao.observeAll()
        .map { list -> list.map { it.icao } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Get the matar data for display, updated when ICAO list is updated
    @OptIn(ExperimentalCoroutinesApi::class)
    val metarFlow: StateFlow<List<Metar>> = icaoCodes
        .flatMapLatest { codes ->
            flow {
                val networkData = weatherApi.getMetar(codes)
                emit(networkData.map { transformer.transform(it) })
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}