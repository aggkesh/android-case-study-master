package com.target.targetcasestudy.data

import android.text.SpannableString

data class DealDetailItem(
    var id: Long,
    var title: String,
    var description: String,
    var regularPrice: SpannableString,
    var salePrice: String,
    var aisle: String,
    var imageURL: String?
)