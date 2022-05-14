package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalSpacing
import com.example.tracker_presentation.R
import com.example.tracker_presentation.components.UnitDisplay
import com.example.tracker_presentation.tracker_overview.TrackerOverViewState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.sp
import com.example.core_ui.CarbColor
import com.example.core_ui.FatColor
import com.example.core_ui.ProteinColor

@Composable
fun NutrientHeader(
    trackerOverViewState: TrackerOverViewState,
    modifier: Modifier = Modifier
){
   val spacing = LocalSpacing.current
   val animatedCaloriesCount = animateIntAsState(targetValue = trackerOverViewState.totalCalories)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomStart = 50.dp,
                    bottomEnd = 50.dp
                )
            )
            .background(MaterialTheme.colors.primary)
            .padding(
                horizontal = spacing.spaceLarge,
                vertical = spacing.spaceExtraLarge
            )
    ) {
       Row(
           modifier = Modifier.fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceBetween
       ) {
         UnitDisplay(
             amount = trackerOverViewState.caloriesGoal,
             unit = stringResource(id = R.string.kcal),
             amountColor = MaterialTheme.colors.onPrimary,
             amountTextSize = 40.sp,
             unitColor = MaterialTheme.colors.onPrimary,
             modifier = Modifier.align(Alignment.Bottom)
             )
           Column{
               Text(
                   text = stringResource(id = R.string.your_goal),
                   style = MaterialTheme.typography.body2,
                   color = MaterialTheme.colors.onPrimary
               )
               UnitDisplay(
                   amount = animatedCaloriesCount.value,
                   unit = stringResource(id = R.string.kcal),
                   amountColor = MaterialTheme.colors.onPrimary,
                   amountTextSize = 40.sp,
                   unitColor = MaterialTheme.colors.onPrimary,
               )
           }
       }
        Spacer(modifier = Modifier.height(spacing.spaceSmall))
        NutrientBar(
            carbs = trackerOverViewState.totalCarbs,
            protein = trackerOverViewState.totalProtIntin,
            fat = trackerOverViewState.totalFat,
            calories = trackerOverViewState.totalCalories,
            caloriesGoal = trackerOverViewState.caloriesGoal,
            modifier = Modifier
                .fillMaxWidth()
                .height(30.dp)
        )
        Spacer(modifier = Modifier.height(spacing.spaceLarge))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NutrientBarInfo(
                value = trackerOverViewState.totalCarbs,
                goal = trackerOverViewState.carbsGoal,
                name = stringResource(id = R.string.carbs),
                color = CarbColor,
                modifier = Modifier.size(90.dp)
            )
            NutrientBarInfo(
                value = trackerOverViewState.totalProtIntin,
                goal = trackerOverViewState.protinGoal,
                name = stringResource(id = R.string.protein),
                color = ProteinColor,
                modifier = Modifier.size(90.dp)
            )
            NutrientBarInfo(
                value = trackerOverViewState.totalFat,
                goal = trackerOverViewState.fatGoal,
                name = stringResource(id = R.string.fat),
                color = FatColor,
                modifier = Modifier.size(90.dp)
            )
        }
    }
}