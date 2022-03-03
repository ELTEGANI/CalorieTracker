package com.example.tracker_domain.use_case


data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val getsFoodForDate: GetsFoodForDate,
    val deleteTrackedFood: DeleteTrackedFood,
    val calculateMealNutrients: CalculateMealNutrients
)
