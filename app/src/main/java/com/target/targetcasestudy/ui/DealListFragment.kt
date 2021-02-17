package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

import com.target.targetcasestudy.R
import com.target.targetcasestudy.viewmodel.DealListViewModel
import com.target.targetcasestudy.viewmodel.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealListFragment : Fragment() {
    private val dealListViewModel: DealListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deal_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        val dealItemAdapter = DealItemAdapter() { dealId ->
            DealListFragmentDirections.actionDealListFragmentToDealItemFragment(dealId).also {
                findNavController().navigate(it)
            }
        }

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        val retryBtn = view.findViewById<Button>(R.id.retry_btn)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view).apply {
            layoutManager = linearLayoutManager
            adapter = dealItemAdapter
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    linearLayoutManager.orientation
                )
            )
            setHasFixedSize(true)
        }

        dealListViewModel.screenState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ScreenState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    retryBtn.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                }
                is ScreenState.Data -> {
                    progressBar.visibility = View.GONE
                    retryBtn.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
                is ScreenState.Error -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.GONE
                    retryBtn.visibility = View.VISIBLE
                }
            }
        })

        dealListViewModel.deals.observe(
            viewLifecycleOwner,
            Observer { dealItemAdapter.submitList(it) })

        retryBtn.setOnClickListener { dealListViewModel.loadDeals() }
    }
}
