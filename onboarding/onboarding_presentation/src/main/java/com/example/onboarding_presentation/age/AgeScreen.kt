package com.example.onboarding_presentation.age

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.domain.model.Gender
import com.example.core.util.UiEvent
import com.example.core_ui.LocalSpacing
import com.example.onboarding_presentation.R
import com.example.onboarding_presentation.components.ActionButton
import com.example.onboarding_presentation.components.SelectableButton
import com.example.onboarding_presentation.components.UnitTextField
import kotlinx.coroutines.flow.collect


@Composable
fun AgeScreen(
    scaffoldState: ScaffoldState,
    onNavigate:(UiEvent.Navigate)->Unit,
    ageViewModel: AgeViewModel = hiltViewModel()
){
    val spacing = LocalSpacing.current
    val context = LocalContext.current

    LaunchedEffect(key1 = true){
        ageViewModel.uiEvent.collect {event->
            when(event){
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.ShowSnackBar->{
                   scaffoldState.snackbarHostState.showSnackbar(
                       message = event.message.asString(context)
                   )
                }
                else -> Unit
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.whats_your_age),
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = ageViewModel.age,
                onValueChange = ageViewModel::onAgeEnter,
                unit = stringResource(id = R.string.years)
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = ageViewModel::onNextClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}