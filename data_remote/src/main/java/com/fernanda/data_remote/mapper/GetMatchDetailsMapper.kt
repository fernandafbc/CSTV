package com.fernanda.data_remote.mapper

import com.fernanda.data_remote.model.MatchDetailsResponse
import com.fernanda.data_remote.model.MatchDetailsTeamResponse
import com.fernanda.data_remote.model.PlayerResponse
import com.fernanda.domain.model.MatchDetailsModel
import com.fernanda.domain.model.MatchDetailsTeamModel
import com.fernanda.domain.model.PlayerModel

object GetMatchDetailsMapper {
    fun MatchDetailsResponse.toDomain() = MatchDetailsModel(
        teams = this.teams.map { it.toDomain() }
    )

    private fun MatchDetailsTeamResponse.toDomain() = MatchDetailsTeamModel(
        image = this.image,
        name = this.name,
        players = this.players.map { it.toDomain() }
    )

    private fun PlayerResponse.toDomain() = PlayerModel(
        firstName = this.firstName,
        lastName = this.lastName,
        image = this.image,
        nickname = this.nickname
    )
}