package com.example.tracker_data.local

import androidx.room.*
import com.example.tracker_data.local.entity.TrackFoodEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TrackerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrackedFood(trackFoodEntity: TrackFoodEntity)

    @Delete
    suspend fun deleteTrackedFood(trackFoodEntity: TrackFoodEntity)

    @Query(
        """
            SELECT *
            FROM trackfoodentity
            WHERE dayOfMonth =:day AND month =:month AND year =:year
        """
    )
    fun getFoodsForDate(day:Int,month:Int,year:Int):Flow<List<TrackFoodEntity>>
}