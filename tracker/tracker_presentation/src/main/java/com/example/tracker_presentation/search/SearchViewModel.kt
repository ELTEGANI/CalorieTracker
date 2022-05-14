package com.example.tracker_presentation.search

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.use_case.FilterOutDigits
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import com.example.tracker_domain.use_case.TrackerUseCases
import com.example.tracker_presentation.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val trackerUseCases: TrackerUseCases,
    private val filterOutDigits: FilterOutDigits
): ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is SearchEvent.OnAmountForFoodChange -> {
                state = state.copy(
                    trackableFood = state.trackableFood.map {
                        if(it.food == event.trackableFood) {
                            it.copy(amount = filterOutDigits(event.amount))
                        } else it
                    }
                )
            }
            is SearchEvent.OnSearch -> {
                executeSearch()
            }
            is SearchEvent.OnToggleTrackableFood -> {
                state = state.copy(
                    trackableFood = state.trackableFood.map {
                        if(it.food == event.trackableFood) {
                            it.copy(isExpanded = !it.isExpanded)
                        } else it
                    }
                )
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
            is SearchEvent.OnTrackFoodClick -> {
                trackFood(event)
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearch = true,
                trackableFood = emptyList()
            )
            trackerUseCases
                .searchFood(state.query)
                .onSuccess { foods ->
                    state = state.copy(
                        trackableFood = foods.map {
                            TrackableFoodUiState(it)
                        },
                        isSearch = false,
                        query = ""
                    )
                }
                .onFailure {
                    Log.d("error",it.message.toString())
                    state = state.copy(isSearch = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackBar(
                            UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
        }
    }

    private fun trackFood(event: SearchEvent.OnTrackFoodClick) {
        viewModelScope.launch {
            val uiState = state.trackableFood.find { it.food == event.trackableFood }
            trackerUseCases.trackFood(
                trackableFood = uiState?.food ?: return@launch,
                amount = uiState.amount.toIntOrNull() ?: return@launch,
                mealType = event.mealType,
                localDate = event.localDate
            )
            _uiEvent.send(UiEvent.NavigateUp)
        }
    }
}