package com.example.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preference.Preferences
import com.example.core.navigation.Route
import com.example.core.util.UiEvent
import com.example.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TrackerOverViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases:TrackerUseCases
):ViewModel(){

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getFoodsForDateJob: Job? = null

    var state by mutableStateOf(TrackerOverViewState())
    private set

    init {
       preferences.saveShouldShowOnBoarding(false)
    }


    fun onEvent(trackerOverViewEvent: TrackerOverViewEvent){
        when(trackerOverViewEvent){
            is TrackerOverViewEvent.OnAddFoodClick ->{
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvent.Navigate(
                            route = Route.SEARCH
                                    + "/${trackerOverViewEvent.meal.mealType.name}"
                                    + "/${state.date.dayOfMonth}"
                                    + "/${state.date.monthValue}"
                                    + "/${state.date.year}"
                        )
                    )
                }
            }
            is TrackerOverViewEvent.OnDeleteTrackedFoodClick->{
                  viewModelScope.launch {
                      trackerUseCases.deleteTrackedFood(trackerOverViewEvent.trackedFood)
                      refreshFoods()
                  }
            }
            is TrackerOverViewEvent.OnNextDayClick ->{
                 state = state.copy(
                     date = state.date.plusDays(1)
                 )
                refreshFoods()
            }
            is TrackerOverViewEvent.OnPreviousDayClick ->{
                state = state.copy(
                    date = state.date.minusDays(1)
                )
                refreshFoods()
            }
            is TrackerOverViewEvent.OnToggleMealClick ->{
                state = state.copy(
                    meals = state.meals.map {
                        if(it.name == trackerOverViewEvent.meal.name){
                            it.copy(isExpand = !it.isExpand)
                        } else it
                    }
                )
            }
        }
    }

    private fun refreshFoods(){
        getFoodsForDateJob?.cancel()
        getFoodsForDateJob = trackerUseCases.getsFoodForDate(state.date)
            .onEach {foods->
                val nutrients = trackerUseCases.calculateMealNutrients(foods)
                state  = state.copy(
                 totalCarbs = nutrients.totalCarbs,
                 totalProtIntin = nutrients.totalProtien,
                 totalFat = nutrients.totalFat,
                 totalCalories = nutrients.totalCalories,
                 carbsGoal = nutrients.carbsGoal,
                 protinGoal = nutrients.protienGoal,
                 fatGoal = nutrients.fatGoal,
                 caloriesGoal = nutrients.caloriesGoal,
                 trackedFoods = foods,
                 meals = state.meals.map {
                     val nutrientsForMeals = nutrients.mealNutrients[it.mealType]
                         ?: return@map it.copy(carbs = 0, protien = 0, fat = 0, calories = 0
                         )
                     it.copy(
                         carbs = nutrientsForMeals.carbs,
                         protien = nutrientsForMeals.protien,
                         fat = nutrientsForMeals.fat,
                         calories = nutrientsForMeals.calories
                     )
                 }
                )
            }.launchIn(viewModelScope)
    }
}