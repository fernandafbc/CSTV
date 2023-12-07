package com.fernanda.data_remote.model

import com.google.gson.annotations.SerializedName

data class MatchResponse (
    @SerializedName("begin_at")
    val beginAt: String
)