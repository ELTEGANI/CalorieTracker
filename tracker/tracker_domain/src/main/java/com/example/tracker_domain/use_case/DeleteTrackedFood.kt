package com.example.tracker_domain.use_case

import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository


class DeleteTrackedFood(
    private val trackerRepository: TrackerRepository
) {
     suspend operator fun invoke(trackedFood: TrackedFood){
       trackerRepository.deleteTrackedFood(trackedFood)
    }
}