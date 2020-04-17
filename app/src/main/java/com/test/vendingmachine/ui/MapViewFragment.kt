package com.test.vendingmachine.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import com.test.vendingmachine.data.DependencyProvider
import com.test.vendingmachine.databinding.FragmentMapBinding
import com.test.vendingmachine.viewmodels.HomeViewModel


class MapViewFragment : BaseFragment<FragmentMapBinding, HomeViewModel>(), OnMapReadyCallback,
    GoogleMap.OnInfoWindowClickListener {

    private var mMap: GoogleMap? = null
    private val TAG_CODE_PERMISSION_LOCATION = 2
    private var mapFragment: SupportMapFragment? = null

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
            val list = getViewModel().listItem.value
            val builder = LatLngBounds.Builder()
            val markerOptions = MarkerOptions()
            list?.forEach {
                markerOptions.title(it.name)
                markerOptions.position(LatLng(it.lat, it.ong))
                builder.include(markerOptions.position)
                val marker = mMap?.addMarker(markerOptions)
                marker?.tag = it
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
            Toast.makeText(activity!!, "Hi", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
       // FragmentHelper.removeFragment(activity?.supportFragmentManager!!, MapViewFragment())
    }

    private fun setupToolbar() {
        displayHomeAsUpEnabled(false)
    }
}