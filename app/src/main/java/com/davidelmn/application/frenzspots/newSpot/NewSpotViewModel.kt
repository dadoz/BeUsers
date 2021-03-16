package com.davidelmn.application.frenzspots.newSpot

import androidx.lifecycle.*
import com.davidelmn.application.frenzspots.Spot
import com.davidelmn.application.frenzspots.data.SpotsDataRepository
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