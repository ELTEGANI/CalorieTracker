package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.R
import com.example.tracker_presentation.tracker_overview.Meal
import androidx.compose.foundation.layout.*
import androidx.compose.ui.unit.sp
import com.example.tracker_presentation.components.NutrientInfo
import com.example.tracker_presentation.components.UnitDisplay


@Composable
fun ExpandableMeal(
    meal:Meal,
    onToggleMealClick:()->Unit,
    content:@Composable () ->Unit,
    modifier: Modifier
){
  val spacing = LocalSpacing.current
    val context = LocalContext.current
  Column(
      modifier = modifier
  ) {
     Row(
         modifier = Modifier
             .fillMaxWidth()
             .clickable {
                 onToggleMealClick()
             }
             .padding(spacing.spaceMedium),
         verticalAlignment = Alignment.CenterVertically
     ) {
        Image(painter = painterResource(id = meal.drawableRes)
            , contentDescription = meal.name.asString(context)
        )
        Spacer(modifier = Modifier.width(spacing.spaceMedium))
         Column(
             modifier = Modifier.weight(1f)
         ) {
           Row(
               horizontalArrangement = Arrangement.SpaceBetween,
               modifier = Modifier.fillMaxWidth()
           ) {
            Text(text =meal.name.asString(context),
                style = MaterialTheme.typography.h3)
            Icon(
                imageVector = if (meal.isExpand) {
                     Icons.Default.KeyboardArrowUp
                }else Icons.Default.KeyboardArrowDown,
                contentDescription = if(meal.isExpand){
                    stringResource(id = R.string.collapse)
                }else stringResource(id = R.string.extend)
            )
           }
             Spacer(modifier = Modifier.width(spacing.spaceSmall))
             Row(
                 modifier = Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.SpaceBetween
             ) {
               UnitDisplay(amount = meal.calories, unit = stringResource(id = R.string.kcal)
               , amountTextSize = 30.sp)
                 Row {
                     NutrientInfo(name = stringResource(id = R.string.carbs)
                         , amount = meal.carbs, unit = stringResource(id = R.string.grams))
                   Spacer(modifier = Modifier.width(spacing.spaceSmall))
                     NutrientInfo(name = stringResource(id = R.string.protein)
                         , amount = meal.protien, unit = stringResource(id = R.string.grams))
                     Spacer(modifier = Modifier.width(spacing.spaceSmall))
                     NutrientInfo(name = stringResource(id = R.string.fat)
                         , amount = meal.fat, unit = stringResource(id = R.string.grams)
                     )
                 }
             }
         }
     }
      Spacer(modifier = Modifier.height(spacing.spaceMedium))
      AnimatedVisibility(visible = meal.isExpand) {
          content()
      }
  }
}