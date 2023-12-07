package com.fernanda.domain.model

data class MatchModel(
    val id: Long? = null,
    val beginAt: String? = null,
    val league: LeagueModel? = null,
    val teams: List<TeamModel?> = emptyList(),
    val serie: SerieModel? = null,
    val status: MatchStatus? = MatchStatus.NOT_STARTED,
)

data class LeagueModel(
    val id: Long? = null,
    val image: String? = null,
    val name: String? = null
)

data class TeamModel(
    val team: TeamDetailsModel? = null
)

data class TeamDetailsModel(
    val id: Long? = null,
    val image: String? = null,
    val name: String? = null
)

data class SerieModel(
    val id: Long? = null,
    val fullName: String? = null
)

enum class MatchStatus(val status: String) {
    RUNNING("running"),
    NOT_STARTED("not_started");

    companion object {
        fun getValue(value: String) = try {
            values().firstOrNull { it.status == value }
        } catch (e: Throwable) {
            NOT_STARTED
        }
    }
}
