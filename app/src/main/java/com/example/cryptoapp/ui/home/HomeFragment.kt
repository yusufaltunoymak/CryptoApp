package com.example.cryptoapp.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.base.BaseFragment
import com.example.cryptoapp.databinding.FragmentHomeBinding
import com.example.cryptoapp.model.home.Data
import com.example.cryptoapp.util.Constants.API_KEY
import com.example.cryptoapp.util.Constants.LIMIT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment() : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate
) {
    private lateinit var cryptoAdapter: HomeRecyclerAdapter

    override val viewModel by viewModels<HomeViewModel>()
    override fun onCreatedFinished() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData(API_KEY, LIMIT)

    }

    override fun initializeListeners() {
    }

    override fun observeEvents() {
        viewModel.cryptoResponse.observe(viewLifecycleOwner) { response ->
            response?.data.let { data ->
                cryptoAdapter = HomeRecyclerAdapter(data as List<Data>)
                binding.rvHome.adapter = cryptoAdapter
                setAdapters()
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            isLoadingHandle(it)
        }

        viewModel.onError.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),it,Toast.LENGTH_SHORT).show()
        }
    }


    private fun setAdapters() {
        binding.rvHome.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = cryptoAdapter
        }
    }

    private fun isLoadingHandle(isLoading:Boolean = false) {
        binding.rvHome.isVisible = !isLoading
        binding.pbHome.isVisible = isLoading
    }
}
