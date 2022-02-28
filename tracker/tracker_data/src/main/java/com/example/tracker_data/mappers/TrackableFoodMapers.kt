package com.example.tracker_data.mappers

import com.example.tracker_data.remote.dto.Product
import com.example.tracker_domain.model.TrackableFood
import kotlin.math.roundToInt


fun Product.toTrackableFood():TrackableFood?{
    val carbsPer100g = nutriments.carbohydrates100g.roundToInt()
    val protienPer100g = nutriments.proteins100g.roundToInt()
    val fatPer100g = nutriments.fat100g.roundToInt()
    val caloresPer100g = nutriments.carbohydrates100g.roundToInt()
    return TrackableFood(
        name = productName ?: return null,
        carbsPer100g = carbsPer100g,
        proteinPer100g = protienPer100g,
        fatPer100g = fatPer100g,
        caloriesPer100g = caloresPer100g,
        imageUrl = imageFrontThumbUrl
    )
}