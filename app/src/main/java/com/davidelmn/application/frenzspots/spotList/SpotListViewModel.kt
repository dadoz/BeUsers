package com.davidelmn.application.frenzspots.spotList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.davidelmn.application.frenzspots.Spot
import com.davidelmn.application.frenzspots.data.SpotsDataRepository

class SpotListViewModel: ViewModel() {
    private val spotListDataRepository = SpotsDataRepository(null)

    val spotList: LiveData<MutableList<Spot>> = spotListDataRepository.getSpots().asLiveData()
}