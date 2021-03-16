package com.davidelmn.application.frenzspots

import android.content.Context
import android.content.res.Resources
import com.davidelmn.application.frenzspots.spotList.SpotListFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import timber.log.Timber
import java.lang.ref.WeakReference
import java.util.logging.Handler

object MapsManager: OnMapReadyCallback {
    private lateinit var callback: (isMapInitialized: Boolean) -> Unit
    private lateinit var contextWeak: WeakReference<Context>

    fun initWithContext(context: Context, onMapInitializedCallback: (isMapInitialized: Boolean) -> Unit) {
         contextWeak = WeakReference(context)
        this.callback = onMapInitializedCallback
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success = googleMap?.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        contextWeak.get(),
                        R.raw.style_json
                    )
                )?: false

            if (!success) {
                Timber.e(SpotListFragment::class.simpleName, "Style parsing failed.")
            }

            callback.invoke(success)
        } catch (e: Resources.NotFoundException) {
            Timber.e(SpotListFragment::class.simpleName, "Can't find style. Error: $e")
        }

        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(45.0703, 7.6869)).zoom(15f).build()
        googleMap?.animateCamera(
            CameraUpdateFactory
            .newCameraPosition(cameraPosition))
    }

}