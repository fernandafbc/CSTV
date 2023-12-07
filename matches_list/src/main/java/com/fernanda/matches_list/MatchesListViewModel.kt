package com.fernanda.matches_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fernanda.domain.model.MatchModel
import com.fernanda.domain.usecase.GetMatchesListUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MatchesListViewModel : ViewModel(), KoinComponent {
    private val getMatchesListUseCase: GetMatchesListUseCase by inject()
    private val navigation: MatchesListNavigation by inject()
    private val _matchesListState: MutableStateFlow<PagingData<MatchModel>> = MutableStateFlow(
        PagingData.empty()
    )
    val matchesList = _matchesListState.asStateFlow()

    suspend fun getMatchesList() {
        getMatchesListUseCase.run()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collectLatest {
                _matchesListState.value = it
            }
    }

    fun goToMatchDetails(matchId: Long, matchLeague: String) {
        navigation.goToMatchDetails(matchId, matchLeague)
    }
}