package com.davidelmn.application.frenzspots.features.spotList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.davidelmn.application.frenzspots.data.SpotsDataRepository
import com.davidelmn.application.frenzspots.models.Spot
import kotlinx.coroutines.ExperimentalCoroutinesApi

class SpotListViewModel: ViewModel() {
    private val spotListDataRepository = SpotsDataRepository(null)
    val isMapLoaded = MutableLiveData(false)

    @ExperimentalCoroutinesApi
    val spotList: LiveData<List<Spot>?> = spotListDataRepository.getSpots().asLiveData()

}