package com.example.tracker_domain.di

import com.example.core.domain.preference.Preferences
import com.example.tracker_domain.model.TrackedFood
import com.example.tracker_domain.repository.TrackerRepository
import com.example.tracker_domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        trackerRepository: TrackerRepository,
        preferences: Preferences
    ): TrackerUseCases {
        return TrackerUseCases(
            trackFood = TrackFood(trackerRepository),
            searchFood = SearchFood(trackerRepository),
            getsFoodForDate = GetFoodsForDate(trackerRepository),
            deleteTrackedFood = DeleteTrackedFood(trackerRepository),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
    }
}