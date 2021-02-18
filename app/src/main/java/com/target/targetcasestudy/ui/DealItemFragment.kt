package com.target.targetcasestudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso

import com.target.targetcasestudy.R
import com.target.targetcasestudy.viewmodel.DealItemViewModel
import com.target.targetcasestudy.viewmodel.ScreenState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DealItemFragment : Fragment() {
    private val dealItemViewModel: DealItemViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deal_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        val retryBtn = view.findViewById<Button>(R.id.retry_btn)
        val dataContainer = view.findViewById<ConstraintLayout>(R.id.data_container)
        val regularPrice = view.findViewById<TextView>(R.id.regular_price)
        val title = view.findViewById<TextView>(R.id.title)
        val salePrice = view.findViewById<TextView>(R.id.sale_price)
        val description = view.findViewById<TextView>(R.id.description)
        val imageView = view.findViewById<ImageView>(R.id.image)

        dealItemViewModel.screenState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ScreenState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                    retryBtn.visibility = View.GONE
                    dataContainer.visibility = View.GONE
                }
                is ScreenState.Data -> {
                    progressBar.visibility = View.GONE
                    retryBtn.visibility = View.GONE
                    dataContainer.visibility = View.VISIBLE
                    val dealItem = it.data
                    title.text = dealItem.title
                    regularPrice.text = dealItem.regularPrice
                    salePrice.text = dealItem.salePrice
                    description.text = dealItem.description
                    Picasso.get().load(dealItem.imageURL).into(imageView)
                }
                is ScreenState.Error -> {
                    progressBar.visibility = View.GONE
                    dataContainer.visibility = View.GONE
                    retryBtn.visibility = View.VISIBLE
                }
            }
        })

        retryBtn.setOnClickListener { dealItemViewModel.loadDealDetail() }
    }
}
