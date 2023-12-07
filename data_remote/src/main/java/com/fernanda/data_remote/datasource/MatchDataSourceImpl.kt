package com.fernanda.data_remote.datasource

import com.fernanda.data.MatchDataSource
import com.fernanda.data_remote.mapper.GetMatchesListMapper.toDomain
import com.fernanda.data_remote.service.WebService
import kotlinx.coroutines.flow.flow

class MatchDataSourceImpl(
    private val webService: WebService
) : MatchDataSource {
    override fun getMatchesList(page: Int) = flow {
        emit(
            webService.getMatchesList(page).map { it.toDomain() }
        )
    }
}