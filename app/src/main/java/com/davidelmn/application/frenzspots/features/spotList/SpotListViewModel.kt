package com.davidelmn.application.frenzspots.features.spotList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.davidelmn.application.frenzspots.models.Spot
import com.davidelmn.application.frenzspots.data.SpotsDataRepository

class SpotListViewModel: ViewModel() {
    private val spotListDataRepository = SpotsDataRepository(null)
    val isMapLoaded = MutableLiveData(false)

    val spotList: LiveData<MutableList<Spot>> = spotListDataRepository.getSpots().asLiveData()

}