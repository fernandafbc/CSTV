package com.fernanda.domain.usecase

import com.fernanda.domain.core.UseCase
import com.fernanda.domain.model.MatchDetailsModel
import com.fernanda.domain.repository.MatchRepository
import kotlinx.coroutines.CoroutineScope

class GetMatchDetailsUseCase(
    scope: CoroutineScope,
    private val repository: MatchRepository
) : UseCase<MatchDetailsModel, String>(scope) {
    override fun run(params: String?) = repository.getMatchDetails(params.orEmpty())
}