package com.target.targetcasestudy.network.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 15/2/2021
 *
 * Response model to parse Deals
 */
data class DealsResponseModel(
    @JsonProperty("products")
    val deals: List<DealResponseModel>
)