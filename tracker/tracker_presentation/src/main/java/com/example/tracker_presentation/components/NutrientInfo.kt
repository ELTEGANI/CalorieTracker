package com.example.tracker_presentation.components

import androidx.compose.compiler.plugins.kotlin.EmptyFunctionMetrics.name
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalSpacing


@Composable
fun NutrientInfo(
    amount:Int,
    unit:String,
    modifier: Modifier = Modifier,
    amountTextSize:TextUnit = 20.sp,
    amountColor: Color = MaterialTheme.colors.onBackground,
    unitTextSize:TextUnit = 14.sp,
    unitColor: Color = MaterialTheme.colors.onBackground,
    nameTextStyle:TextStyle = MaterialTheme.typography.body1
){
  Column(
      modifier = modifier,
      horizontalAlignment = Alignment.CenterHorizontally
  ){
    UnitDisplay(
        amount = amount,
        unit = unit,
        amountTextSize = amountTextSize,
        amountColor = amountColor,
        unitTextSize = unitTextSize,
        unitColor = unitColor
        )
      Text(
          text = name,
          color = MaterialTheme.colors.onBackground,
          style = nameTextStyle,
          fontWeight = FontWeight.Light
      )
  }
}