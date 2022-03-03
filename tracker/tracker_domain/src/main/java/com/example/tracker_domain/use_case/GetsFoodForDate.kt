package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import kotlin.math.roundToInt


class GetsFoodForDate(
    private val trackerRepository: TrackerRepository
) {
     operator fun invoke(localDate:LocalDate):Flow<List<TrackedFood>>{
      return trackerRepository.getFoodsForDate(localDate)
    }
}