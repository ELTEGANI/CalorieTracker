package com.example.tracker_data.repository

import com.example.tracker_data.local.TrackerDao
import com.example.tracker_data.mappers.toTrackFoodEntity
import com.example.tracker_data.mappers.toTrackableFood
import com.example.tracker_data.mappers.toTrackedFood
import com.example.tracker_data.remote.OpenFoodApi
import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception
import java.time.LocalDate

class TrackerRepositoryIm(
    private val trackerDao: TrackerDao,
    private val openFoodApi: OpenFoodApi
):TrackerRepository{
    override suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>> {
        return try {
             val searchDto = openFoodApi.searchFood(
                 query = query,
                 page = page,
                 pageSize = pageSize
             )
            Result.success(searchDto.products.mapNotNull { it.toTrackableFood() })
        }catch (e:Exception){
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun insertTrackedFood(trackedFood: TrackedFood) {
        return trackerDao.insertTrackedFood(trackedFood.toTrackFoodEntity())
    }

    override suspend fun deleteTrackedFood(trackedFood: TrackedFood) {
        return trackerDao.deleteTrackedFood(trackedFood.toTrackFoodEntity())
    }

    override fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>> {
        return trackerDao.getFoodsForDate(
            day = localDate.dayOfMonth,
            month = localDate.monthValue,
            year = localDate.year
        ).map {entities->
            entities.map {
                it.toTrackedFood()
            }
        }
    }
}