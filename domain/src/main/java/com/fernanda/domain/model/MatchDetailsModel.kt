package com.fernanda.domain.model

data class MatchDetailsModel(
    val teams: List<MatchDetailsTeamModel> = emptyList()
)

data class MatchDetailsTeamModel(
    val image: String? = null,
    val name: String? = null,
    val players: List<PlayerModel> = emptyList()
)

data class PlayerModel(
    val firstName: String? = null,
    val lastName: String? = null,
    val image: String? = null,
    val nickname: String? = null,
)