package com.example.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.tracker_overview.components.DaySelector
import com.example.tracker_presentation.tracker_overview.components.NutrientHeader


@Composable
fun TrackerOverViewScreen(
    onNavigate:(UiEvent.Navigate)->Unit,
    viewModel:TrackerOverViewModel = hiltViewModel()
){
  val spacing = LocalSpacing.current
  val state = viewModel.state
  val context = LocalSpacing.current
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
              OnNextDayClick = {
               viewModel.onEvent(TrackerOverViewEvent.OnNextDayClick)
              },
              modifier = Modifier
              .fillMaxSize()
              .padding(horizontal = spacing.spaceMedium)
          )
          Spacer(modifier = Modifier.height(spacing.spaceMedium))
      }
  }
}