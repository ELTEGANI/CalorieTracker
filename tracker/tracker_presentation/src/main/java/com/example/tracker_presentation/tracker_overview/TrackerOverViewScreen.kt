package com.example.tracker_presentation.tracker_overview

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.R
import com.example.tracker_presentation.tracker_overview.components.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext


@Composable
fun TrackerOverViewScreen(
    onNavigate:(UiEvent.Navigate)->Unit,
    viewModel:TrackerOverviewViewModel = hiltViewModel()
){
  val spacing = LocalSpacing.current
  val state = viewModel.state
  val context = LocalContext.current
  LaunchedEffect(key1 = context){
      viewModel.uiEvent.collect{event->
          when(event){
              is UiEvent.Navigate -> onNavigate(event)
              else -> Unit
          }
      }
  }
  LazyColumn(
      modifier = Modifier
          .fillMaxSize()
          .padding(bottom = spacing.spaceMedium)
  ){
      item {
          NutrientHeader(trackerOverViewState = state)
          Spacer(modifier = Modifier.height(spacing.spaceMedium))
          DaySelector(
              localDate = state.date,
              onPreviousDayClick = {
                viewModel.onEvent(TrackerOverViewEvent.OnPreviousDayClick)
              },
              onNextDayClick = {
               viewModel.onEvent(TrackerOverViewEvent.OnNextDayClick)
              },
              modifier = Modifier
                  .fillMaxSize()
                  .padding(horizontal = spacing.spaceMedium)
          )
          Spacer(modifier = Modifier.height(spacing.spaceMedium))
      }
      items(state.meals){meal->
          ExpandableMeal(
              meal = meal,
              onToggleMealClick = {viewModel.onEvent(TrackerOverViewEvent.OnToggleMealClick(meal))},
              content = {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = spacing.spaceSmall)
                        ) {
                      state.trackedFoods.forEach {food->
                          TrackedFoodItem(
                              trackedFood = food,
                              onDeleteClick = {
                                  viewModel.onEvent(TrackerOverViewEvent.OnDeleteTrackedFoodClick(food)
                                  )
                              }
                          )
                       Spacer(modifier = Modifier.height(spacing.spaceMedium))
                      }
                    AddButton(text = stringResource(id = R.string.add_meal,
                    meal.name.asString(context)), onClick = {
                        viewModel.onEvent(TrackerOverViewEvent.OnAddFoodClick(meal)
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                        )
                  }
              },
              modifier = Modifier.fillMaxWidth()
          )
      }
  }
}