package com.example.lifesemantics.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifesemantics.R
import com.example.lifesemantics.data.entity.Item
import com.example.lifesemantics.databinding.FragmentHomeBinding
import com.example.lifesemantics.listener.ItemClickListener
import com.example.lifesemantics.util.LocationProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentHomeBinding

    private val mainViewModel: HomeViewModel by viewModels()

    private val adapter by lazy { RecyclerViewAdapter(this) }

    private lateinit var navController: NavController

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
                    Toast.makeText(context, "위치 접근 권한 허용이 필요합니다.", Toast.LENGTH_SHORT).show()
                    requireActivity().finish()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        requestMultiplePermission.launch(permissionList)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        navController = Navigation.findNavController(view)

        getMyLocation()

        binding.buttonSearch.setOnClickListener {
            if (binding.editTextSearch.text.toString().isEmpty()) {
                Toast.makeText(context, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                mainViewModel.getHospitalInfo(
                    binding.editTextSearch.text.toString(),
                    latitude,
                    longitude
                )
            }
        }

        binding.btnPageNext.setOnClickListener {
            mainViewModel.nextInfo()
        }

        binding.btnPagePrevious.setOnClickListener {
            mainViewModel.previousInfo()
        }

        mainViewModel.hospitalInfo.observe(viewLifecycleOwner, Observer {
            if (it.body?.items?.itemList == null) {
                binding.recyclerView.visibility = View.GONE
                binding.tvSearchResult.visibility = View.GONE
                binding.tvResultNull.visibility = View.VISIBLE
            } else {
                it.body?.items?.itemList?.let { itemList ->
                    adapter.submitList(itemList)
                    val searchResultMessage = getString(R.string.search_result, itemList.size)
                    binding.tvSearchResult.text = searchResultMessage
                    binding.tvResultNull.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.tvSearchResult.visibility = View.VISIBLE
                }
            }
        })

        mainViewModel.cnt.observe(viewLifecycleOwner, Observer {
            binding.tvPageNum.text = it.toString()
        })

        super.onViewCreated(view, savedInstanceState)
    }
    private fun getMyLocation() {
        val locationProvider = LocationProvider(requireContext())
        latitude = locationProvider.getLocationLatitude()
        longitude = locationProvider.getLocationLongitude()
    }

    override fun onClick(item: Item) {
        val bundle = Bundle().apply {
            putParcelable("data", item)
        }
        navController.navigate(R.id.homeFragment_to_detailfragmnet, bundle)
    }
}