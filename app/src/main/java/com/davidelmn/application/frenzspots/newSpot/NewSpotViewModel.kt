package com.davidelmn.application.frenzspots.newSpot

import androidx.lifecycle.ViewModel
import com.davidelmn.application.frenzspots.Spot
import com.davidelmn.application.frenzspots.data.SpotsDataRepository

class NewSpotViewModel: ViewModel() {

    private val spotDataRepository = SpotsDataRepository(null)

    fun addSpot(spot: Spot) {
        spotDataRepository.addSpot(spot)
    }
}