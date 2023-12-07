package com.fernanda.data_remote.model

import com.google.gson.annotations.SerializedName

data class MatchDetailsResponse(
    @SerializedName("opponents")
    val teams: List<MatchDetailsTeamResponse> = emptyList()
)

data class MatchDetailsTeamResponse(
    @SerializedName("image_url")
    val image: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("players")
    val players: List<PlayerResponse> = emptyList()
)

data class PlayerResponse(
    @SerializedName("first_name")
    val firstName: String? = null,
    @SerializedName("last_name")
    val lastName: String? = null,
    @SerializedName("image_url")
    val image: String? = null,
    @SerializedName("name")
    val nickname: String? = null,
)