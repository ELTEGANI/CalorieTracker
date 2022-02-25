package com.example.onboarding_presentation.nutrient_goal

sealed class NutrientGaolEvent{
    data class OnCarbRatioEnter(val ratio:String):NutrientGaolEvent()
    data class OnProtienEnter(val ratio:String):NutrientGaolEvent()
    data class OnCarbFatEnter(val ratio:String):NutrientGaolEvent()
    object OnNextClick:NutrientGaolEvent()
}
