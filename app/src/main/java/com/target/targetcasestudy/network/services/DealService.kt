package com.target.targetcasestudy.network.services

import com.target.targetcasestudy.network.model.DealsResponseModel
import com.target.targetcasestudy.network.model.DealResponseModel
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Keshav Aggarwal 15/2/2021
 *
 * Service used to interact with Deal
 */
interface DealService {
    /**
     * fetch all deals
     */
    @GET("deals")
    suspend fun fetchDeals() : DealsResponseModel

    /**
     * fetch deal with given deal id
     */
    @GET("deals/{id}")
    suspend fun fetchDeal(@Path("id") dealId: String) : DealResponseModel
}