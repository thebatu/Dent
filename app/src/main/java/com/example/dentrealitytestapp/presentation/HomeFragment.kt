package com.example.dentrealitytestapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.dentrealitytestapp.R
import com.example.dentrealitytestapp.databinding.FragmentDetailsBinding
import com.example.dentrealitytestapp.databinding.FragmentHomefragmentBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.example.dentrealitytestapp.models.CountriesModel
import com.example.dentrealitytestapp.models.toLocation
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class HomeFragment: Fragment(), GoogleMap.OnInfoWindowClickListener, OnMapReadyCallback{

    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var map: GoogleMap
    private lateinit var binding: FragmentHomefragmentBinding


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        getDisplayMarkers(googleMap)
        googleMap.setOnInfoWindowClickListener(this)
    }

    private fun getDisplayMarkers(googleMap: GoogleMap) {
        lifecycleScope.async {
            viewModel.getCountries()?.forEach { it ->
                displayMarkersOptions(googleMap, it)
            }
        }
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(countries.get(0).toLocation()))
    }

    private fun displayMarkersOptions(googleMap: GoogleMap, country: CountriesModel){
        googleMap.addMarker(
            MarkerOptions()
                .snippet(country.capital)
                .title(country.name)
                .position(country.toLocation())
        )
    }

    override fun onInfoWindowClick(marker: Marker) {
        val title = marker.title
        if (title != null) { //TODO insure no null or use simpler syntax
            //TODO extract navigation logic
            val action = HomeFragmentDirections.actionMapsFragmentToDetailsFragment(title)
            findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_homefragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomefragmentBinding.bind(view)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

    }
}