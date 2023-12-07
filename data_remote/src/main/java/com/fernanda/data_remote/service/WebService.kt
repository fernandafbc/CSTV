package com.fernanda.data_remote.service

import com.fernanda.data_remote.model.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {

    @GET(MatchConstants.GET_MATCHES_LIST)
    suspend fun getMatchesList(
        @Query("page") page: Int,
        @Query("sort") sort: String = "begin_at",
        @Query("filter[status]") statusFilter: String = "running,not_started"
    ): List<MatchResponse>

    private object MatchConstants {
        const val GET_MATCHES_LIST = "csgo/matches"
    }
}