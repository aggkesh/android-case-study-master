package com.target.targetcasestudy.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.target.targetcasestudy.network.model.PriceResponseModel

const val PRODUCT_ID = 0L

@Entity(tableName = "products")
data class Product(
    @PrimaryKey
    val productId: Long,
    val title: String,
    val imageURL: String?,
    val aisle: String,
    val description: String,
) {
    var regularPriceId: Long = PRODUCT_ID
    var salePriceId: Long? = null
}