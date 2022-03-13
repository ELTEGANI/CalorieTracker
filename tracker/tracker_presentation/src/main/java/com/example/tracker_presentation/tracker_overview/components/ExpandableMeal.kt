package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.tracker_overview.Meal


@Composable
fun ExpandableMeal(
    meal:Meal,
    onToggleMealClick:()->Unit,
    content:@Composable () ->Unit,
    modifier: Modifier
){
  val spacing = LocalSpacing.current
  Column(
      modifier = modifier
  ) {
     Row(
         modifier = Modifier.fillMaxWidth()
             .clickable {
                 onToggleMealClick()
             }
             .padding(spacing.spaceMedium),
         verticalAlignment = Alignment.CenterVertically
     ) {

     }
  }
}