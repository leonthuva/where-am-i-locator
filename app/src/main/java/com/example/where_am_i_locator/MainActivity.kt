package com.example.where_am_i_locator

import android.Manifest
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : ComponentActivity() {

    private lateinit var locationRepository: LocationRepository

    private lateinit var tvLatitude: TextView
    private lateinit var tvLongitude: TextView
    private lateinit var tvAccuracy: TextView
    private lateinit var tvTimestamp: TextView
    private lateinit var btnGetLocation: Button

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            fetchLocation()
        } else {
            Toast.makeText(this, R.string.permission_required, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationRepository = LocationRepository(this)

        tvLatitude = findViewById(R.id.tvLatitude)
        tvLongitude = findViewById(R.id.tvLongitude)
        tvAccuracy = findViewById(R.id.tvAccuracy)
        tvTimestamp = findViewById(R.id.tvTimestamp)
        btnGetLocation = findViewById(R.id.btnGetLocation)

        btnGetLocation.setOnClickListener {
            checkPermissionAndFetchLocation()
        }
    }

    private fun checkPermissionAndFetchLocation() {
        if (PermissionHelper.hasLocationPermission(this)) {
            fetchLocation()
        } else {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun fetchLocation() {
        locationRepository.getCurrentLocation(
            onSuccess = { location ->
                tvLatitude.text = getString(R.string.latitude_label, location.latitude.toString())
                tvLongitude.text = getString(R.string.longitude_label, location.longitude.toString())
                tvAccuracy.text = getString(R.string.accuracy_label, location.accuracy.toString())
                val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                tvTimestamp.text = getString(R.string.timestamp_label, sdf.format(Date(location.time)))
            },
            onFailure = { exception ->
                Toast.makeText(this, getString(R.string.location_failure, exception.message), Toast.LENGTH_LONG).show()
            }
        )
    }

}
