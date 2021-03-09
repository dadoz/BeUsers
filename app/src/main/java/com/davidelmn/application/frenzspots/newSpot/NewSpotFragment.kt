package com.davidelmn.application.frenzspots.newSpot

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.davidelmn.application.frenzspots.Spot
import com.davidelmn.application.frenzspots.databinding.NewSpotFragmentBinding
import com.davidelmn.application.frenzspots.databinding.SpotListFragmentBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NewSpotFragment : Fragment() {

    private lateinit var newSpotViewModel: NewSpotViewModel
    private lateinit var binding: NewSpotFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return NewSpotFragmentBinding.inflate(inflater, container, false).also { binding = it }.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        newSpotViewModel = ViewModelProvider(this).get(NewSpotViewModel::class.java)
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