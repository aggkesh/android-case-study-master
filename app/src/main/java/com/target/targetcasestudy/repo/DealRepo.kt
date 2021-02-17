package com.target.targetcasestudy.repo

import android.util.Log
import com.target.targetcasestudy.database.*
import com.target.targetcasestudy.database.convertor.CloudToDao
import com.target.targetcasestudy.network.services.DealService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Keshav Aggarwal 15/2/2021
 *
 * Repo to interact with [DealService]
 */
class DealRepo @Inject constructor(
    private val dealService: DealService,
    private val dealDao: DealDao
) {
    private val TAG = DealRepo::class.java.name

    fun loadDeals() = dealDao.getDealProductsAndPrices()

    suspend fun loadDeal(dealId: Long): ProductWithPrice? {
        return fetchAndLoadDealFromAPI(dealId)
    }

    private suspend fun fetchAndLoadDealFromAPI(dealId: Long): ProductWithPrice? {
        return try {
            dealService.fetchDeal(dealId.toString()).also {
                CloudToDao.convertToProductAndPriceDao(it).also { dao ->
                    saveDeals(dao.first, dao.second, dao.third)
                }
            }
            loadDealFromDb(dealId)
        } catch (exception: Exception) {
            Log.d(TAG, "Error while loading data :- " + exception.message)
            null
        }
    }

    @Throws(Exception::class)
    suspend fun fetchAndSaveDeals() {
        val dealsResponseModel = dealService.fetchDeals()
        dealsResponseModel.products.forEach {
            CloudToDao.convertToProductAndPriceDao(it).also { dao ->
                saveDeals(dao.first, dao.second, dao.third)
            }
        }
    }

    private suspend fun saveDeals(product: Product, regularPrice: Price, salePrice: Price?) =
        withContext(Dispatchers.IO) {
            dealDao.insertProductWithPrice(product, regularPrice, salePrice)
        }

    private suspend fun loadDealFromDb(dealId: Long) = withContext(Dispatchers.IO) {
        dealDao.getDealProductAndPrice(dealId)
    }
}