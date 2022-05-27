package com.plcoding.calorytracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core.domain.preference.Preferences
import com.plcoding.calorytracker.navigation.Route
import com.example.onboarding_presentation.activity.ActivityLevelScreen
import com.example.onboarding_presentation.age.AgeScreen
import com.example.onboarding_presentation.gender.GenderScreen
import com.example.onboarding_presentation.goal.GoalScreen
import com.example.onboarding_presentation.height.HeightScreen
import com.example.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import com.example.onboarding_presentation.weight.WeightScreen
import com.example.onboarding_presentation.welcome.WelcomeScreen
import com.example.tracker_presentation.search.SearchScreen
import com.example.tracker_presentation.tracker_overview.TrackerOverViewScreen
import com.plcoding.calorytracker.ui.theme.CaloryTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var preferences: Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val shouldShowOnBoarding  = preferences.loadShouldShowOnboarding()
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
                        startDestination = if(shouldShowOnBoarding){
                            Route.WELCOME
                        }else Route.TRACKER_OVERVIEW
                    ){
                        composable(Route.WELCOME){
                            WelcomeScreen(onNextClick ={
                                navController.navigate(Route.GENEDER)
                            })
                        }
                        composable(Route.AGE){
                           AgeScreen(scaffoldState = scaffoldState,
                               onNextClick = {
                                   navController.navigate(Route.HEIGHT)
                               })
                        }
                        composable(Route.GENEDER){
                            GenderScreen(onNextClick = {
                                navController.navigate(Route.AGE)
                            })
                        }
                        composable(Route.HEIGHT){
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.WEIGHT)
                                }
                            )
                        }
                        composable(Route.WEIGHT){
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNextClick = {
                                    navController.navigate(Route.ACTIVITY)
                                }
                            )
                        }
                        composable(Route.NUTRIENT_GOAL){
                           NutrientGoalScreen(
                               scaffoldState = scaffoldState,
                               onNextClick = {
                                   navController.navigate(Route.TRACKER_OVERVIEW)
                               }
                           )
                        }
                        composable(Route.ACTIVITY){
                            ActivityLevelScreen(
                                onNextClick = {
                                    navController.navigate(Route.GOAL)
                                }
                            )
                        }
                        composable(Route.GOAL){
                            GoalScreen(
                                onNextClick = {
                                    navController.navigate(Route.NUTRIENT_GOAL)
                                }
                            )
                        }
                        composable(Route.TRACKER_OVERVIEW){
                            TrackerOverViewScreen(
                                onNavigateToSearch = {mealName,day,month,year->
                                    navController.navigate(
                                        Route.SEARCH +
                                                "/$mealName" +
                                                "/$day" +
                                                "/$month" +
                                                "/$year"

                                    )
                                }
                            )
                        }
                        composable(
                            Route.SEARCH + "/{mealName}/{dayOfMonth}/{month}/{year}",
                             arguments = listOf(
                                 navArgument("mealName"){
                                     type = NavType.StringType
                                 },
                                 navArgument("dayOfMonth"){
                                     type = NavType.IntType
                                 },
                                 navArgument("month"){
                                     type = NavType.IntType
                                 },
                                 navArgument("year"){
                                     type = NavType.IntType
                                 },
                             )){
                            val mealName = it.arguments?.getString("mealName")!!
                            val dayOfMonth = it.arguments?.getInt("dayOfMonth")!!
                            val month = it.arguments?.getInt("month")!!
                            val year = it.arguments?.getInt("year")!!
                            SearchScreen(
                                scaffoldState = scaffoldState,
                                mealName = mealName,
                                dayOfMonth = dayOfMonth,
                                month = month,
                                year = year,
                                onNavigateUp = {
                                   navController.navigateUp()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}