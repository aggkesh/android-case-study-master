package com.target.targetcasestudy.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DealDao {
    @Transaction
    @Query("Select * from products")
    fun getDealProductsAndPrices(): Flow<List<ProductWithPrice>>

    @Transaction
    @Query("Select * from products where productId=:dealId")
    fun getDealProductAndPrice(dealId: Long): ProductWithPrice?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPrice(price: Price): Long

    @Transaction
    suspend fun insertProductWithPrice(product: Product, regularPrice: Price, salePrice: Price?) {
        insertProduct(product.apply {
            regularPriceId = insertPrice(regularPrice)
            salePriceId = salePrice?.let { insertPrice(it) }
        })
    }
}