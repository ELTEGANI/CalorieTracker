package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import java.time.LocalDate
import kotlin.math.roundToInt


class TrackFood(
    private val trackerRepository: TrackerRepository
) {
    suspend operator fun invoke(
    trackableFood: TrackableFood,
    amount:Int,
    mealType: MealType,
    localDate: LocalDate
    ){
      trackerRepository.insertTrackedFood(
          TrackedFood(
              name = trackableFood.name,
              carbs = ((trackableFood.carbsPer100g / 100f) * amount).roundToInt(),
              protien = ((trackableFood.proteinPer100g / 100f) * amount).roundToInt(),
              fat = ((trackableFood.fatPer100g / 100f) * amount).roundToInt(),
              calories = ((trackableFood.caloriesPer100g / 100f) * amount).roundToInt(),
              imageUrl = trackableFood.imageUrl,
              mealType = mealType,
              amount = amount,
              date = localDate
          )
      )
    }
}