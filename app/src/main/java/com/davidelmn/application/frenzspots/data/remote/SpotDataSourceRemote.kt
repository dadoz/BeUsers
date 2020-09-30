package com.davidelmn.application.frenzspots.data.remote


import android.content.Context
import com.davidelmn.application.frenzspots.Spot
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.gson.Gson
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import timber.log.Timber

class SpotDataSourceRemote(var context: Context?) {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun getSpotList(): Flow<MutableList<Spot>> = callbackFlow {

        val eventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot
                    .getValue<HashMap<String, Any>>()
                    ?.let { ArrayList(it.values) }
                    ?.map { Gson().toJson(it) }
                    ?.map { Gson().fromJson(it, Spot::class.java) }

                //coroutine proceed
                offer(items)

                Timber.d("Value is: $items")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Timber.e("Failed to read value. ${error.toException()}")

                //coroutine cancel
                cancel(
                    message = error.message,
                    cause =  error.toException().cause
                )
            }
        }

        firebaseDatabase
            .getReference("/")
            .addValueEventListener(eventListener)

        awaitClose {
            Timber.d("cancelling the listener on collection")
            firebaseDatabase.getReference("/").removeEventListener(eventListener)
        }
    }

    fun addSpot(spot: Spot) {
        firebaseDatabase
            .getReference("/").apply {
                val id = this.push().key
                id?.let {
                    this.child(id).setValue(spot)
                }
            }
    }
}