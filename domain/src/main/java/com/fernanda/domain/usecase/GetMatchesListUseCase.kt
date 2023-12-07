package com.fernanda.domain.usecase

import androidx.paging.PagingData
import com.fernanda.domain.core.UseCase
import com.fernanda.domain.model.MatchModel
import com.fernanda.domain.repository.MatchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

class GetMatchesListUseCase(
    scope: CoroutineScope,
    private val repository: MatchRepository
) : UseCase<PagingData<MatchModel>, Unit>(scope) {
    override fun run(params: Unit?): Flow<PagingData<MatchModel>> =
        repository.getMatchesList()
}