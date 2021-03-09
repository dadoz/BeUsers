package com.davidelmn.application.frenzspots.spotList

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davidelmn.application.frenzspots.R
import com.davidelmn.application.frenzspots.databinding.SpotListFragmentBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SpotListFragment : Fragment() {
    private val spotViewModel by lazy {
        ViewModelProvider(this).get(SpotListViewModel::class.java)
    }

    private lateinit var binding: SpotListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return SpotListFragmentBinding.inflate(layoutInflater, container, false).also { binding = it }.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spotViewModel.spotList.observe(viewLifecycleOwner, { spotList ->
            spotList?.let {
                binding.fsSpotRecyclerViewId.apply {
                adapter = SpotListAdapter(spotList)
                layoutManager = LinearLayoutManager(context)
            }
        }})

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_SpotListFragment_to_NewSpotFragment)
        }
    }
}