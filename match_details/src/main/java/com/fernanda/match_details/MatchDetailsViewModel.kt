package com.fernanda.match_details

import androidx.lifecycle.ViewModel
import com.fernanda.domain.model.MatchDetailsModel
import com.fernanda.domain.usecase.GetMatchDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MatchDetailsViewModel : ViewModel(), KoinComponent {
    private val getMatchDetailsUseCase: GetMatchDetailsUseCase by inject()

    private val _matchDetailsState = MutableStateFlow(MatchDetailsModel())

    val matchDetails = _matchDetailsState.asStateFlow()

    suspend fun getMatchDetails(matchId: String) {
        getMatchDetailsUseCase.run(matchId).collectLatest {
            _matchDetailsState.value = it
        }
    }
}