package com.example.lifesemantics.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifesemantics.R
import com.example.lifesemantics.databinding.ActivityMainBinding
import com.example.lifesemantics.util.LocationProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private val adapter by lazy { RecyclerViewAdapter() }

    // 위도
    private var latitude: Double = 0.0

    // 경도
    private var longitude: Double = 0.0

    private val permissionList = arrayOf(
        android.Manifest.permission.ACCESS_COARSE_LOCATION,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
    )

    private val requestMultiplePermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            result.forEach {
                if (!it.value) {
                    Toast.makeText(this, "${it.key}권한 허용 필요", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        requestMultiplePermission.launch(permissionList)

        getMyLocation()

        binding.buttonSearch.setOnClickListener {
            mainViewModel.getHospitalInfo(
                binding.editTextSearch.text.toString(),
                latitude,
                longitude
            )
        }

        mainViewModel.hospitalInfo.observe(this, Observer {
            it.body?.items?.itemList?.let { itemList ->
                adapter.submitList(itemList)
                val searchResultMessage = getString(R.string.search_result, itemList.size)
                binding.tvSearchResult.text = searchResultMessage
                binding.tvSearchResult.visibility = View.VISIBLE
            }

        })
    }

    private fun getMyLocation() {
        val locationProvider = LocationProvider(this)
        latitude = locationProvider.getLocationLatitude()
        longitude = locationProvider.getLocationLongitude()
    }
}