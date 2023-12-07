package com.fernanda.data_remote.model

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("begin_at")
    val beginAt: String? = null,
    @SerializedName("league")
    val league: LeagueResponse? = null,
    @SerializedName("opponents")
    val teams: List<TeamResponse> = emptyList(),
    @SerializedName("serie")
    val serie: SerieResponse? = null,
    @SerializedName("status")
    val status: String? = null,
)

data class LeagueResponse(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("image_url")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class TeamResponse(
    @SerializedName("opponent")
    val team: TeamDetailsResponse? = null
)

data class TeamDetailsResponse(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("image_url")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null
)

data class SerieResponse(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("full_name")
    val fullName: String? = null
)