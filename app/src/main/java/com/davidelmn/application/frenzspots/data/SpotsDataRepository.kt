package com.davidelmn.application.frenzspots.data

import android.content.Context
import com.davidelmn.application.frenzspots.data.local.SpotDataSourceLocal
import com.davidelmn.application.frenzspots.data.remote.SpotDataSourceRemote
import com.davidelmn.application.frenzspots.models.Spot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow

class SpotsDataRepository(val context: Context?) {
    private val bookmarkDataSourceLocal = SpotDataSourceLocal(context)
    private val bookmarkDataSourceRemote = SpotDataSourceRemote(context)

    @ExperimentalCoroutinesApi
    fun getSpots(): Flow<List<Spot>?> {
//        if (local != 0) else remote
        return bookmarkDataSourceRemote.getSpotList()
    }

    fun addSpot(spot: Spot): Flow<Boolean> {
        return bookmarkDataSourceRemote.addSpot(spot)
    }

    fun updateSpot(spot: Spot) {
//        return bookmarkDataSourceLocal.updateSpot(spot)
    }

    fun findSpotById(id: String): Spot {
        return Spot("bla", "bla", "blal", "blal", 0.0, 0.0)
//        return bookmarkDataSourceLocal.findSpotById(id)
    }

    fun deleteSpot(spot: Spot) {
//        return bookmarkDataSourceLocal.deleteSpot(spot)
    }
}