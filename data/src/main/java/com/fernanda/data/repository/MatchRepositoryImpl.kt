package com.fernanda.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fernanda.data.MatchDataSource
import com.fernanda.data.repository.paging.MatchPagingSource
import com.fernanda.domain.model.MatchModel
import com.fernanda.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow

class MatchRepositoryImpl(
    private val dataSource: MatchDataSource
) : MatchRepository {
    override fun getMatchesList(): Flow<PagingData<MatchModel>> {
        return Pager(
            config = PagingConfig(pageSize = 50, prefetchDistance = 2),
            pagingSourceFactory = {
                MatchPagingSource(dataSource)
            }
        ).flow
    }

    override fun getMatchDetails(id: String) = dataSource.getMatchDetails(id)
}