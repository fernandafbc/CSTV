package com.fernanda.data_remote.mapper

import com.fernanda.data_remote.model.LeagueResponse
import com.fernanda.data_remote.model.MatchResponse
import com.fernanda.data_remote.model.SerieResponse
import com.fernanda.data_remote.model.TeamDetailsResponse
import com.fernanda.data_remote.model.TeamResponse
import com.fernanda.domain.model.LeagueModel
import com.fernanda.domain.model.MatchModel
import com.fernanda.domain.model.MatchStatus
import com.fernanda.domain.model.SerieModel
import com.fernanda.domain.model.TeamDetailsModel
import com.fernanda.domain.model.TeamModel

object GetMatchesListMapper {
    fun MatchResponse.toDomain() = MatchModel(
        id = this.id ?: 0,
        beginAt = this.beginAt.orEmpty(),
        league = this.league?.toDomain(),
        teams = teams.map { it.toDomain() },
        serie = this.serie?.toDomain(),
        status = this.status?.let { MatchStatus.getValue(it) }
    )

    private fun LeagueResponse?.toDomain() = LeagueModel(
        id = this?.id ?: 0,
        image = this?.image.orEmpty(),
        name = this?.name.orEmpty()
    )

    private fun TeamResponse.toDomain() = TeamModel(
        team = this.team?.toDomain()
    )

    private fun TeamDetailsResponse.toDomain() = TeamDetailsModel(
        id = this.id ?: 0,
        image = this.image.orEmpty(),
        name = this.name.orEmpty()
    )

    private fun SerieResponse.toDomain() = SerieModel(
        id = this.id ?: 0,
        fullName = this.fullName.orEmpty()
    )
}