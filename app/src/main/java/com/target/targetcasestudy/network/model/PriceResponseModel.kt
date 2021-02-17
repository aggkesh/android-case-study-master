package com.target.targetcasestudy.network.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Created by Keshav Aggarwal 15/2/2021
 *
 * Response model to parse Price
 */
data class PriceResponseModel(
    @JsonProperty("amount_in_cents")
    val amountInCents: Int,
    @JsonProperty("currency_symbol")
    val currencySymbol: String,
    @JsonProperty("display_string")
    val displayString: String
)