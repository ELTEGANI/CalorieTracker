package com.example.tracker_data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tracker_data.local.TrackerDao


@Database(entities = [TrackFoodEntity::class],version = 1)
abstract class TrackerDataBase: RoomDatabase(){
   abstract val trackerDao:TrackerDao
}