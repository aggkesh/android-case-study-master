package com.target.targetcasestudy.database.convertor

import com.target.targetcasestudy.database.Price
import com.target.targetcasestudy.database.Deal
import com.target.targetcasestudy.network.model.PriceResponseModel
import com.target.targetcasestudy.network.model.DealResponseModel

object CloudToDao {

    fun convertToDealAndPriceDao(dealResponseModel: DealResponseModel): Triple<Deal, Price, Price?> {
        return Triple(
            dealResponseModelToDeal(dealResponseModel),
            priceResponseModelToPrice(dealResponseModel.regularPrice),
            dealResponseModel.salePrice?.let { priceResponseModelToPrice(dealResponseModel.salePrice) }
        )
    }

    fun dealResponseModelToDeal(dealResponseModel: DealResponseModel): Deal {
        return Deal(
            dealId = dealResponseModel.id,
            title = dealResponseModel.title,
            imageURL = dealResponseModel.imageURL,
            aisle = dealResponseModel.aisle,
            description = dealResponseModel.description
        )
    }

    fun priceResponseModelToPrice(priceResponseModel: PriceResponseModel): Price {
        return Price(
            amountInCents = priceResponseModel.amountInCents,
            currencySymbol = priceResponseModel.currencySymbol,
            displayString = priceResponseModel.displayString
        )
    }
}