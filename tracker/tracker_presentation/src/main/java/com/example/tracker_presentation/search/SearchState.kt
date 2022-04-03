package com.example.tracker_presentation.search


data class SearchState(
    val query:String ="",
    val isHintVisible:Boolean = false,
    val isSearch:Boolean = false,
    val trackableFood: List<TrackableFoodUiState> = emptyList()
)
