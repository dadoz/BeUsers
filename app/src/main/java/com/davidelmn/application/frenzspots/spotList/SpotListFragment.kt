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
import kotlinx.android.synthetic.main.spot_list_fragment.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SpotListFragment : Fragment() {
    lateinit var spotViewModel: SpotListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.spot_list_fragment, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        spotViewModel = ViewModelProvider(this).get(SpotListViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spotViewModel.spotList.observe(viewLifecycleOwner, { spotList ->
            spotList?.let {
            fsSpotRecyclerViewId.apply {
                adapter = SpotListAdapter(spotList)
                layoutManager = LinearLayoutManager(context)
            }
        }})

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_SpotListFragment_to_NewSpotFragment)
        }
    }
}