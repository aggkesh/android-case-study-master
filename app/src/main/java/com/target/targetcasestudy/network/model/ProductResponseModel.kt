package com.target.targetcasestudy.network.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 15/2/2021
 *
 * Response model to parse Product
 */
data class ProductResponseModel(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("title")
    val title: String,
    @JsonProperty("image_url")
    val imageURL: String?,
    @JsonProperty("aisle")
    val aisle: String,
    @JsonProperty("description")
    val description: String,
    @JsonProperty("regular_price")
    val regularPrice: PriceResponseModel,
    @JsonProperty("sale_price")
    val salePrice: PriceResponseModel?
)