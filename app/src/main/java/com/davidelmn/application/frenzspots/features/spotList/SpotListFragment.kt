package com.davidelmn.application.frenzspots.features.spotList

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import com.davidelmn.application.frenzspots.R
import com.davidelmn.application.frenzspots.databinding.SpotListFragmentBinding
import com.davidelmn.application.frenzspots.managers.MapsManager
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.*
import com.google.android.material.card.MaterialCardView


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SpotListFragment : Fragment() {
    private val spotViewModel by lazy {
        ViewModelProvider(this).get(SpotListViewModel::class.java)
    }

    private lateinit var binding: SpotListFragmentBinding
    private lateinit var bottomSheet: BottomSheetBehavior<MaterialCardView>

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
                binding.let {
                    it.fsSpotRecyclerViewId.apply {
                        adapter = SpotListAdapter(spotList)
                        isNestedScrollingEnabled = false
                        addOnScrollListener(object : RecyclerView.OnScrollListener() {
                            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                                (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                                    .takeIf {
                                        (layoutManager?.itemCount
                                            ?: 0 - recyclerView.childCount) > it && it == 0 && dy < 0
                                    }?.let {
                                        bottomSheet.state = STATE_COLLAPSED
                                    } ?: kotlin.run {
                                    if (bottomSheet.state != STATE_COLLAPSED)
                                        bottomSheet.state = STATE_EXPANDED
                                }
                            }
                        })
                    }

                    val hiddenView = it.hiddenExpandedLayoutId
                    val mainView = it.mainExpandedLayoutId
                    it.fsPersonalAddressCardId.setOnClickListener {
                        TransitionManager.beginDelayedTransition(it as CardView, AutoTransition())
                        mainView.visibility = if (mainView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                        hiddenView.visibility = if (hiddenView.visibility == View.VISIBLE) View.GONE else View.VISIBLE
                    }
                }
            }
        })

        bottomSheet = BottomSheetBehavior.from(binding.bottomSheet).apply {
            addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    state = when (newState) {
                        STATE_DRAGGING -> STATE_EXPANDED
                        else -> newState
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }

            })
        }
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