package com.fernanda.match_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.fernanda.domain.model.MatchDetailsModel
import com.fernanda.domain.usecase.GetMatchDetailsUseCase
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MatchDetailsViewModel : ViewModel(), KoinComponent {
    private val getMatchDetailsUseCase: GetMatchDetailsUseCase by inject()
    private val navigation: MatchDetailsNavigation by inject()

    private val _matchDetailsState = mutableStateOf(MatchDetailsModel())
    val matchDetails get() = _matchDetailsState.value

    suspend fun getMatchDetails(matchId: String) {
        getMatchDetailsUseCase.run(matchId).collectLatest {
            _matchDetailsState.value = it
        }
    }

    fun popBackStack() {
        navigation.popBackStack()
    }
}