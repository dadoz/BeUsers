package com.davidelmn.application.frenzspots.spotList

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidelmn.application.frenzspots.R
import com.davidelmn.application.frenzspots.databinding.SpotListFragmentBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import timber.log.Timber


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SpotListFragment : Fragment(), OnMapReadyCallback {
    private val spotViewModel by lazy {
        ViewModelProvider(this).get(SpotListViewModel::class.java)
    }

    private lateinit var binding: SpotListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = SpotListFragmentBinding.inflate(layoutInflater, container, false)
        .apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = spotViewModel
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spotViewModel.spotList.observe(viewLifecycleOwner, { spotList ->
            spotList?.let {
                binding.fsSpotRecyclerViewId.apply {
                    adapter = SpotListAdapter(spotList)
                    layoutManager = LinearLayoutManager(context)
                }
            }
        })

        (childFragmentManager.findFragmentByTag("mapFragmentTag") as SupportMapFragment).getMapAsync(
            this
        )
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_SpotListFragment_to_NewSpotFragment)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            val success =
                googleMap?.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                        context,
                        R.raw.style_json
                    )
                )
            if (success == false) {
                Timber.e(SpotListFragment::class.simpleName, "Style parsing failed.")
            }
        } catch (e: Resources.NotFoundException) {
            Timber.e(SpotListFragment::class.simpleName, "Can't find style. Error: $e")
        }

        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(45.0703, 7.6869)).zoom(15f).build()
        googleMap?.animateCamera(CameraUpdateFactory
                .newCameraPosition(cameraPosition))
    }
}