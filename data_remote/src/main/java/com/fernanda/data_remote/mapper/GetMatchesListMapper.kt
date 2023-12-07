package com.fernanda.data_remote.mapper

import com.fernanda.data_remote.model.MatchResponse
import com.fernanda.domain.model.MatchModel

object GetMatchesListMapper {
    fun listToDomain(listResponse: List<MatchResponse>?): List<MatchModel> {
        val list = mutableListOf<MatchModel>()
        listResponse?.forEach {
            list.add(it.toDomain())
        }
        return list.toList()
    }

    private fun MatchResponse.toDomain() = MatchModel(
        beginAt = this.beginAt
    )
}