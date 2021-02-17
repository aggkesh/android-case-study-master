package com.target.targetcasestudy.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty

const val PRICE_ID = 0L

@Entity(tableName = "prices")
data class Price(
    val amountInCents: Int,
    val currencySymbol: String,
    val displayString: String
) {
    @PrimaryKey(autoGenerate = true)
    var priceId: Long = PRICE_ID
}