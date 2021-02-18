package com.target.targetcasestudy.database

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import androidx.room.Embedded
import androidx.room.Relation
import com.target.targetcasestudy.data.DealDetailItem
import com.target.targetcasestudy.data.DealItem

data class DealWithPrice(
    @Embedded
    val deal: Deal,
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

fun DealWithPrice.toDealItem(): DealItem {
    return DealItem(
        id = deal.dealId,
        title = deal.title,
        price = salePrice?.displayString ?: regularPrice.displayString,
        aisle = deal.aisle,
        imageURL = deal.imageURL
    )
}

fun DealWithPrice.toDealDetailItem(): DealDetailItem {
    return DealDetailItem(
        id = deal.dealId,
        title = deal.title,
        description = deal.description,
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
        aisle = deal.aisle,
        imageURL = deal.imageURL
    )
}