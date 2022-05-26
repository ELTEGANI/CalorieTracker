package com.plcoding.calorytracker

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preference.Preferences
import com.example.core.domain.use_case.FilterOutDigits
import com.example.tracker_domain.use_case.*
import com.example.tracker_presentation.search.SearchViewModel
import com.example.tracker_presentation.tracker_overview.TrackerOverviewViewModel
import com.plcoding.calorytracker.repository.TrackerRepositoryFake
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@HiltAndroidTest
class TrackerOverViewE2E {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()
    private lateinit var trackerRepositoryFake: TrackerRepositoryFake
    private lateinit var trackerUseCases: TrackerUseCases
    private lateinit var preferences: Preferences
    private lateinit var trackerOverviewViewModel:TrackerOverviewViewModel
    private lateinit var searchViewModel:SearchViewModel
    private lateinit var navController: NavController

    @Before
    fun setUp(){
        hiltRule.inject()
        preferences = mockk(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        trackerRepositoryFake = TrackerRepositoryFake()
        trackerUseCases = TrackerUseCases(
            trackFood = TrackFood(trackerRepositoryFake),
            searchFood = SearchFood(trackerRepositoryFake),
            getsFoodForDate = GetFoodsForDate(trackerRepositoryFake),
            deleteTrackedFood = DeleteTrackedFood(trackerRepositoryFake),
            calculateMealNutrients = CalculateMealNutrients(preferences)
        )
        trackerOverviewViewModel = TrackerOverviewViewModel(
            preferences = preferences,
            trackerUseCases = trackerUseCases
        )
        searchViewModel = SearchViewModel(
            trackerUseCases = trackerUseCases,
            filterOutDigits =  FilterOutDigits()
        )
        composeRule.setContent {

        }
    }
}