package com.example.tracker_presentation.tracker_overview

import androidx.lifecycle.ViewModel
import com.example.core.domain.preference.Preferences
import com.example.core.util.UiEvent
import com.example.tracker_domain.use_case.TrackerUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TrackerOverViewModel @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases:TrackerUseCases
):ViewModel(){

    init {
       preferences.saveShouldShowOnBoarding(false)
    }


    fun onEvent(trackerOverViewEvent: TrackerOverViewEvent){
        when(trackerOverViewEvent){
            is TrackerOverViewEvent.OnAddFoodClick ->{

            }
            is TrackerOverViewEvent.OnDeleteTrackedFoodClick->{

            }
            is TrackerOverViewEvent.OnNextDayClick ->{

            }
            is TrackerOverViewEvent.OnPreviousDayClick ->{

            }
            is TrackerOverViewEvent.OnToggleMealClick ->{

            }
        }
    }
}