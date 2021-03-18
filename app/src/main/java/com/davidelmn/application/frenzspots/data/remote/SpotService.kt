package com.davidelmn.application.frenzspots.data.remote

import com.davidelmn.application.frenzspots.models.Spot
import kotlinx.coroutines.flow.Flow


interface SpotService {

//    @GET("/")
    fun retrieveSpotList(): Flow<Spot>

}