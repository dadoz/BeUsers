package com.davidelmn.application.frenzspots.spotList

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.davidelmn.application.frenzspots.MapsManager
import com.davidelmn.application.frenzspots.R
import com.davidelmn.application.frenzspots.databinding.SpotListFragmentBinding
import com.google.android.gms.maps.SupportMapFragment


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
    ): View = SpotListFragmentBinding.inflate(layoutInflater, container, false)
        .apply {
            binding = this
            lifecycleOwner = viewLifecycleOwner
            viewModel = spotViewModel
            setHasOptionsMenu(true)
        }
        .root

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.spot_list_menu, menu)

        // Associate searchable configuration with the SearchView
        activity?.getSystemService(Context.SEARCH_SERVICE)?.let { searchManager ->
            menu.findItem(R.id.search)?.actionView?.let {
                (it as SearchView).apply {
                    setSearchableInfo((searchManager as SearchManager).getSearchableInfo(activity?.componentName))
                }
            }
        }
        return
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spotViewModel.spotList.observe(viewLifecycleOwner, { spotList ->
            spotList?.let {
                binding.fsSpotRecyclerViewId.apply {
                    adapter = SpotListAdapter(spotList)
                }
            }
        })

        context?.let {
            MapsManager.apply {
                initWithContext(it) {
                    spotViewModel.isMapLoaded.value = it
                }
                childFragmentManager.findFragmentByTag("mapFragmentTag")?.let {
                    (it as SupportMapFragment).getMapAsync(this)
                }
            }
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_SpotListFragment_to_NewSpotFragment)
        }
    }

}