package com.target.targetcasestudy.database.convertor

import com.target.targetcasestudy.database.Price
import com.target.targetcasestudy.database.Product
import com.target.targetcasestudy.network.model.PriceResponseModel
import com.target.targetcasestudy.network.model.ProductResponseModel

object CloudToDao {

    fun convertToProductAndPriceDao(productResponseModel: ProductResponseModel): Triple<Product, Price, Price?> {
        return Triple(
            productResponseModelToProduct(productResponseModel),
            priceResponseModelToPrice(productResponseModel.regularPrice),
            productResponseModel.salePrice?.let { priceResponseModelToPrice(productResponseModel.salePrice) }
        )
    }

    fun productResponseModelToProduct(productResponseModel: ProductResponseModel): Product {
        return Product(
            productId = productResponseModel.id,
            title = productResponseModel.title,
            imageURL = productResponseModel.imageURL,
            aisle = productResponseModel.aisle,
            description = productResponseModel.description
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