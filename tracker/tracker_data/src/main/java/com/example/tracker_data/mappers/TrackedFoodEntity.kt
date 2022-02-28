package com.example.tracker_data.mappers

import com.example.tracker_data.local.entity.TrackFoodEntity
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import java.time.LocalDate


fun TrackFoodEntity.toTrackedFood():TrackedFood{
    return TrackedFood(
        name = name,
        carbs = carbs,
        protien = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
        id = id
    )
}

fun TrackedFood.toTrackFoodEntity():TrackFoodEntity{
    return TrackFoodEntity(
        name = name,
        carbs = carbs,
        protein = protien,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.monthValue,
        month = date.monthValue,
        year =date.dayOfYear,
        calories = calories,
        id = id
    )
}