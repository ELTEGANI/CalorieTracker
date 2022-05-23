package com.plcoding.calorytracker.repository

import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class TrackerRepositoryFake : TrackerRepository{
    private val trackedFood = mutableListOf<TrackedFood>()
    var searchResults = listOf<TrackableFood>()
    var shouldReturnError = listOf<TrackableFood>()

    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertTrackedFood(food: TrackedFood) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTrackedFood(food: TrackedFood) {
        TODO("Not yet implemented")
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        TODO("Not yet implemented")
    }
}