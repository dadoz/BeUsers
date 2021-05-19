package com.davidelmn.application.frenzspots.features.newSpot

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidelmn.application.frenzspots.data.SpotsDataRepository
import com.davidelmn.application.frenzspots.models.Spot
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewSpotViewModel: ViewModel() {
    private val spotDataRepository = SpotsDataRepository(null)
    var isSpotDataAddedSuccess: MutableLiveData<Boolean> = MutableLiveData(false)
    val isMapLoaded = MutableLiveData<Boolean>()

    fun addSpot(spot: Spot) {
        viewModelScope.launch {
            spotDataRepository.addSpot(spot).collect {
                isSpotDataAddedSuccess.value = it
            }
        }
    }
}