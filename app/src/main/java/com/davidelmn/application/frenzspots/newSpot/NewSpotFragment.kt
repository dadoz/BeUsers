package com.davidelmn.application.frenzspots.newSpot

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.davidelmn.application.frenzspots.R
import com.davidelmn.application.frenzspots.Spot
import com.davidelmn.application.frenzspots.spotList.SpotListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.new_spot_fragment.*
import kotlinx.android.synthetic.main.new_spot_fragment.view.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewSpotFragment : Fragment() {

    private lateinit var newSpotViewModel: NewSpotViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.new_spot_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        newSpotViewModel = ViewModelProvider(this).get(NewSpotViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fsNewSpotSaveButtonId.setOnClickListener {
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
    }

    private fun createSpot(): Spot {
        val title = fsNewSpotTitleEditTextId.text.toString()
        val address = fsNewSpotAddressEditTextId.text.toString()
        val city = fsNewSpotCityEditTextId.text.toString()

        return Spot(title, address, "temporary_empty", city)
    }

}