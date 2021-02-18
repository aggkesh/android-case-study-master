package com.target.targetcasestudy.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DealDao {
    @Transaction
    @Query("Select * from deals")
    fun getDeals(): Flow<List<DealWithPrice>>

    @Transaction
    @Query("Select * from deals where dealId=:dealId")
    fun getDeal(dealId: Long): DealWithPrice?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeal(deal: Deal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrice(price: Price): Long

    @Transaction
    suspend fun insertDealWithPrice(deal: Deal, regularPrice: Price, salePrice: Price?) {
        insertDeal(deal.apply {
            regularPriceId = insertPrice(regularPrice)
            salePriceId = salePrice?.let { insertPrice(it) }
        })
    }
}