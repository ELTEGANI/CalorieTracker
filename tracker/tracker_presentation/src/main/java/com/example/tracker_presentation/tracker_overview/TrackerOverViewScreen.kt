package com.example.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
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
      }
  }
}