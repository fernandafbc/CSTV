package com.fernanda.domain.repository

import androidx.paging.PagingData
import com.fernanda.domain.model.MatchDetailsModel
import com.fernanda.domain.model.MatchModel
import kotlinx.coroutines.flow.Flow

interface MatchRepository {
    fun getMatchesList() : Flow<PagingData<MatchModel>>
    fun getMatchDetails(id: String): Flow<MatchDetailsModel>
}