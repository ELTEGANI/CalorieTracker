package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import kotlin.math.roundToInt


class GetFoodsForDate(
    private val repository: TrackerRepository
) {

    operator fun invoke(date: LocalDate): Flow<List<TrackedFood>> {
        return repository.getFoodsForDate(date)
    }
}