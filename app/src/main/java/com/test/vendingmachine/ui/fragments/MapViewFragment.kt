package com.test.vendingmachine.ui.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.test.core.base.BaseFragment
import com.test.vendingmachine.R
import com.test.vendingmachine.data.di.DependencyProvider
import com.test.vendingmachine.databinding.FragmentMapBinding
import com.test.vendingmachine.utilities.ItemBeer
import com.test.vendingmachine.utilities.ItemCoffee
import com.test.vendingmachine.utilities.ItemSnacks
import com.test.vendingmachine.viewmodels.HomeViewModel


class MapViewFragment : BaseFragment<FragmentMapBinding, HomeViewModel>(), OnMapReadyCallback,
    GoogleMap.OnInfoWindowClickListener {

    private var mMap: GoogleMap? = null
    private val TAG_CODE_PERMISSION_LOCATION = 2
    private var mapFragment: SupportMapFragment? = null

    companion object {
        var onClick: ((name: String) -> Unit)? = null
    }

    override fun initializeController() {
    }

    override fun initializeLayoutId(): Int {
        return R.layout.fragment_map
    }

    override fun initializeViews(savedInstanceState: Bundle?) {
        setupToolbar()
        mapFragment =
            childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment?.getMapAsync(this)


    }

    override fun getViewModel(): HomeViewModel {
        val viewModelFactory = DependencyProvider.provideHomeViewModelFactory(activity!!)

        return ViewModelProvider(activity!!, viewModelFactory).get(HomeViewModel::class.java)
    }

    override fun getBindingVariable(): Int {
        return 0
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap
        if (ContextCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            mMap?.setOnInfoWindowClickListener(this)
            val list = getViewModel().mListItem.value
            val builder = LatLngBounds.Builder()
            val markerOptions = MarkerOptions()
            list?.forEach {
                when (it) {
                    is ItemSnacks -> {
                        markerOptions.title(it.vn_detail.vn_name)
                        markerOptions.position(LatLng(it.vn_detail.lat, it.vn_detail.long))
                        builder.include(markerOptions.position)
                        val marker = mMap?.addMarker(markerOptions)
                        marker?.tag = it
                    }
                    is ItemBeer -> {
                        markerOptions.title(it.vn_detail.vn_name)
                        markerOptions.position(LatLng(it.vn_detail.lat, it.vn_detail.long))
                        builder.include(markerOptions.position)
                        val marker = mMap?.addMarker(markerOptions)
                        marker?.tag = it
                    }
                    is ItemCoffee -> {
                        markerOptions.title(it.vn_detail.vn_name)
                        markerOptions.position(LatLng(it.vn_detail.lat, it.vn_detail.long))
                        builder.include(markerOptions.position)
                        val marker = mMap?.addMarker(markerOptions)
                        marker?.tag = it
                    }

                }
            }
            val bounds = builder.build()
            val cu = CameraUpdateFactory.newLatLngBounds(bounds, 0)
            mMap?.animateCamera(cu)
            mMap?.isMyLocationEnabled = true
        } else {
            ActivityCompat.requestPermissions(
                activity!!, arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ),
                TAG_CODE_PERMISSION_LOCATION
            )
            mapFragment?.getMapAsync(this)

        }
    }

    override fun onInfoWindowClick(marker: Marker?) {
        marker?.let {
            marker.title.let {
                onClick?.invoke(it)
            }
        }
    }

    private fun setupToolbar() {
        displayHomeAsUpEnabled(false)
    }

    override fun onStop() {
        super.onStop()
        getViewModel().mListItem = MutableLiveData()
    }
}