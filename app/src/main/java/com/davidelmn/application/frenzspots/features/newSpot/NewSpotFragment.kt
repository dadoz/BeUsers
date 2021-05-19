package com.davidelmn.application.frenzspots.features.newSpot

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.davidelmn.application.frenzspots.R
import com.davidelmn.application.frenzspots.databinding.NewSpotFragmentBinding
import com.davidelmn.application.frenzspots.managers.MapsManager
import com.davidelmn.application.frenzspots.models.Spot
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewSpotFragment : Fragment() {

    private val newSpotViewModel: NewSpotViewModel by lazy {
        ViewModelProvider(this).get(NewSpotViewModel::class.java)
    }
    private lateinit var binding: NewSpotFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = NewSpotFragmentBinding.inflate(inflater, container, false)
        .apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = newSpotViewModel
            setHasOptionsMenu(true)
        }
        .root

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.new_spot_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fsNewSpotSaveButtonId.setOnClickListener {
            newSpotViewModel.addSpot(spot = createSpot())
        }

        newSpotViewModel.isSpotDataAddedSuccess.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    Snackbar.make(view, "success", Snackbar.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
                false -> Snackbar.make(view, "error", Snackbar.LENGTH_SHORT).show()
            }
        })

        context?.let {
            MapsManager.apply {
                initWithContext(it) {
                    isMapInitialized -> newSpotViewModel.isMapLoaded.value = isMapInitialized
                }
                childFragmentManager.findFragmentByTag("mapFragmentTag")?.let {
                    (it as SupportMapFragment).getMapAsync(this)
                }
            }
        }

    }

    private fun createSpot(): Spot {
        return binding.let {
            val title = it.fsNewSpotTitleEditTextId.text.toString()
            val address = it.fsNewSpotAddressEditTextId.text.toString()
            val city = it.fsNewSpotCityEditTextId.text.toString()
            Spot(title, address, "temporary_empty", city)
        }
    }

}