package com.davidelmn.application.frenzspots.spotlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.davidelmn.application.frenzspots.Spot
import com.davidelmn.application.frenzspots.data.SpotListDataRepository

class SpotListViewModel: ViewModel() {
    private val spotListDataRepository = SpotListDataRepository(null)

    val spotList: LiveData<MutableList<Spot>> = spotListDataRepository.getSpots().asLiveData()
}