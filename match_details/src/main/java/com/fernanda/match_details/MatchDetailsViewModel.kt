package com.fernanda.match_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fernanda.domain.model.MatchDetailsModel
import com.fernanda.domain.usecase.GetMatchDetailsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MatchDetailsViewModel : ViewModel(), KoinComponent {
    private val getMatchDetailsUseCase: GetMatchDetailsUseCase by inject()
    private val navigation: MatchDetailsNavigation by inject()

    private val _matchDetailsState = mutableStateOf(MatchDetailsModel())
    private val _isLoading = mutableStateOf(true)
    val matchDetails get() = _matchDetailsState.value
    val isLoading get() = _isLoading.value

    fun getMatchDetails(matchId: String) {
        getMatchDetailsUseCase(
            params = matchId,
            onSuccess = {
                _matchDetailsState.value = it
                _isLoading.value = false
            }
        )
    }

    fun popBackStack() {
        navigation.popBackStack()
    }
}