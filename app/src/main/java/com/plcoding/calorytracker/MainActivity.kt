package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.core.navigation.Route
import com.example.onboarding_presentation.activity.ActivityLevelScreen
import com.example.onboarding_presentation.age.AgeScreen
import com.example.onboarding_presentation.gender.GenderScreen
import com.example.onboarding_presentation.goal.GoalScreen
import com.example.onboarding_presentation.height.HeightScreen
import com.example.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.example.onboarding_presentation.weight.WeightScreen
import com.example.onboarding_presentation.welcome.WelcomeScreen
import com.example.tracker_presentation.tracker_overview.TrackerOverViewScreen
import com.plcoding.calorytracker.navigation.navigate
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloryTrackerTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                    ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.WELCOME
                    ){
                        composable(Route.WELCOME){
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.AGE){
                           AgeScreen(scaffoldState = scaffoldState,onNavigate = navController::navigate)
                        }
                        composable(Route.GENEDER){
                            GenderScreen(onNavigate = navController::navigate)
                        }
                        composable(Route.HEIGHT){
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.WEIGHT){
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.NUTRIENT_GOAL){
                           NutrientGoalScreen(
                               scaffoldState = scaffoldState,
                               onNavigate = navController::navigate
                           )
                        }
                        composable(Route.ACTIVITY){
                            ActivityLevelScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.GOAL){
                            GoalScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW){
                            TrackerOverViewScreen(
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Route.SEARCH){

                        }
                    }
                }

            }
        }
    }
}