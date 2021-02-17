package com.target.targetcasestudy.data

import com.target.targetcasestudy.database.Price

data class DealItem(
    var id: Long,
    var title: String,
    var price: String,
    var aisle: String,
    var imageURL: String?
)