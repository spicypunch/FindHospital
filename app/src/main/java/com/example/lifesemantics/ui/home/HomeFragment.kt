package com.example.lifesemantics.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lifesemantics.R
import com.example.lifesemantics.data.entity.Item
import com.example.lifesemantics.databinding.FragmentHomeBinding
import com.example.lifesemantics.listener.ItemClickListener
import com.example.lifesemantics.util.LocationProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.logging.Logger

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

    // 권한 리스트
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
        // 위치접근 권한을 먼저 확인한다.
        requestMultiplePermission.launch(permissionList)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        navController = Navigation.findNavController(view)
        // 먼저 현재 내 위치 정보를 가져와 latitude와 longitude에 넣어준다.
        getMyLocation()

        // 검색 텍스트 여백을 검사한 후 API 요청을 한다.
        binding.buttonSearch.setOnClickListener {
            if (binding.editTextSearch.text.toString().isEmpty()) {
                Toast.makeText(context, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
            } else {
                binding.progressBar.visibility = View.VISIBLE
                viewLifecycleOwner.lifecycleScope.launch {
                    viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                        launch {
                            mainViewModel.getHospitalInfo(
                                binding.editTextSearch.text.toString(),
                                latitude,
                                longitude
                            ).collect() { pagingData ->
                                binding.progressBar.visibility = View.GONE
                                adapter.submitData(lifecycle, pagingData)
//                                val searchResultMessage = getString(R.string.search_result, pagingData)
                                binding.apply {
//                                    tvSearchResult.text = searchResultMessage
                                    tvResultNull.visibility = View.GONE
                                    recyclerView.visibility = View.VISIBLE
                                    tvSearchResult.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                }
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getMyLocation() {
        val locationProvider = LocationProvider(requireContext())
        latitude = locationProvider.getLocationLatitude()
        longitude = locationProvider.getLocationLongitude()
    }

    // Adapter에서 넘어온 데이터를 Bundle에 저장하고 navController에 넘긴다.
    override fun onClick(item: Item) {
        val bundle = Bundle().apply {
            putParcelable("data", item)
        }
        navController.navigate(R.id.homeFragment_to_detailfragmnet, bundle)
    }
}