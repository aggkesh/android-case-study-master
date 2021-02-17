package com.target.targetcasestudy.database

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import androidx.room.Embedded
import androidx.room.Relation
import com.target.targetcasestudy.data.DealDetailItem
import com.target.targetcasestudy.data.DealItem

data class ProductWithPrice(
    @Embedded
    val product: Product,
    @Relation(
        parentColumn = "regularPriceId",
        entityColumn = "priceId"
    )
    val regularPrice: Price,
    @Relation(
        parentColumn = "salePriceId",
        entityColumn = "priceId"
    )
    val salePrice: Price?
)

fun ProductWithPrice.toDealItem(): DealItem {
    return DealItem(
        id = product.productId,
        title = product.title,
        price = salePrice?.displayString ?: regularPrice.displayString,
        aisle = product.aisle,
        imageURL = product.imageURL
    )
}

fun ProductWithPrice.toDealDetailItem(): DealDetailItem {
    return DealDetailItem(
        id = product.productId,
        title = product.title,
        description = product.description,
        regularPrice = SpannableString(regularPrice.displayString).apply {
            salePrice?.let {
                setSpan(
                    StrikethroughSpan(),
                    0,
                    length,
                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        },
        salePrice = salePrice?.displayString ?: "",
        aisle = product.aisle,
        imageURL = product.imageURL
    )
}