package com.fernanda.data

import com.fernanda.domain.model.MatchModel
import kotlinx.coroutines.flow.Flow

interface MatchDataSource {
    fun getMatchesList(page: Int) : Flow<List<MatchModel>>
}