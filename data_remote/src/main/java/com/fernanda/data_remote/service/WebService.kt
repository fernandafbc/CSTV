package com.fernanda.data_remote.service

import com.fernanda.data_remote.model.MatchDetailsResponse
import com.fernanda.data_remote.model.MatchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebService {

    @GET(MatchConstants.GET_MATCHES_LIST)
    suspend fun getMatchesList(
        @Query("page") page: Int,
        @Query("sort") sort: String = "begin_at",
        @Query("filter[status]") statusFilter: String = "running,not_started"
    ): List<MatchResponse>

    @GET(MatchConstants.GET_MATCH_DETAILS)
    suspend fun getMatchDetails(
        @Path(MatchConstants.MATCH_ID) id: String
    ) : MatchDetailsResponse

    private object MatchConstants {
        const val GET_MATCHES_LIST = "csgo/matches"
        const val MATCH_ID = "match_id_or_slug"
        const val GET_MATCH_DETAILS = "matches/{$MATCH_ID}/opponents"
    }
}