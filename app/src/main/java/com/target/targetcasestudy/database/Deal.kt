package com.target.targetcasestudy.database

import androidx.room.Entity
import androidx.room.PrimaryKey

const val DEAL_ID = 0L

@Entity(tableName = "deals")
data class Deal(
    @PrimaryKey
    val dealId: Long,
    val title: String,
    val imageURL: String?,
    val aisle: String,
    val description: String
) {
    var regularPriceId: Long = DEAL_ID
    var salePriceId: Long? = null
}