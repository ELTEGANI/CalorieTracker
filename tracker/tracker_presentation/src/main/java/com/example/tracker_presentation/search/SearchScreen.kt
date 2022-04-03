package com.example.tracker_presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.util.UiEvent
import com.example.core.util.UiText
import com.example.core_ui.LocalSpacing
import com.example.tracker_domain.model.MealType
import com.example.tracker_presentation.R
import com.example.tracker_presentation.search.components.SearchTextField
import com.example.tracker_presentation.search.components.TrackableFoodItem
import kotlinx.coroutines.flow.collect
import java.time.LocalDate


@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    mealName:String,
    dayOfMonth:Int,
    month:Int,
    year:Int,
    onNavigateUp: () -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
){
   val spacing  = LocalSpacing.current
   val state = searchViewModel.state
   val context = LocalContext.current
   val keyBoardController = LocalSoftwareKeyboardController.current
   LaunchedEffect(key1 = keyBoardController){
       searchViewModel.uiEvent.collect{event->
             when(event){
                 is UiEvent.ShowSnackBar -> {
                     scaffoldState.snackbarHostState.showSnackbar(
                         message = event.message.asString(context)
                     )
                     keyBoardController?.hide()
                 }
                 is UiEvent.NavigateUp -> onNavigateUp()
                 else -> Unit
             }
       }
   }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(spacing.spaceMedium)
    ) {
       Text(text = stringResource(id = R.string.add_meal,
         mealName
       ), style = MaterialTheme.typography.h2)
       Spacer(modifier = Modifier.height(spacing.spaceMedium))
       SearchTextField(
           text = state.query,
           onValueChange = {
                searchViewModel.onEvent(SearchEvent.OnQueryChange(it))
           },
           onSearch = {
                searchViewModel.onEvent(SearchEvent.OnSearch)
           },
           onFocusChanged = {
                searchViewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
           }
       )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.trackableFood) { food ->
                TrackableFoodItem(
                    trackableFoodUiState = food,
                    onClick = {
                        searchViewModel.onEvent(SearchEvent.OnToggleTrackableFood(food.food))
                    },
                    onAmountChange = {
                        searchViewModel.onEvent(SearchEvent.OnAmountForFoodChange(
                            food.food, it
                        ))
                    },
                    onTrack = {
                        searchViewModel.onEvent(
                            SearchEvent.OnTrackFoodClick(
                                trackableFood = food.food,
                                mealType = MealType.fromString(mealName),
                                localDate = LocalDate.of(year, month, dayOfMonth)
                            )
                        )
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
      when{
         state.isSearch -> CircularProgressIndicator()
         state.trackableFood.isEmpty() -> {
             Text(text = stringResource(id = R.string.no_results), style = MaterialTheme.typography.body1,
             textAlign = TextAlign.Center)
         }
      }
    }
}