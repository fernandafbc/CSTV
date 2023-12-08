package com.fernanda.data_remote

import com.fernanda.data.MatchDataSource
import com.fernanda.data_remote.datasource.MatchDataSourceImpl
import com.fernanda.data_remote.mapper.GetMatchDetailsMapper.toDomain
import com.fernanda.data_remote.mapper.GetMatchesListMapper.toDomain
import com.fernanda.data_remote.model.MatchDetailsResponse
import com.fernanda.data_remote.model.MatchResponse
import com.fernanda.data_remote.service.WebService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class MatchDataSourceImplTest {

    @Mock
    private lateinit var webService: WebService
    private lateinit var dataSource: MatchDataSource


    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dataSource = MatchDataSourceImpl(webService)
    }

    @Test
    fun `getMatchesList must return MatchModel list when called`() = runBlocking {
        val dummyResponse = listOf(MatchResponse())
        stubGetMatchesList(dummyResponse)

        val data = dummyResponse.map { it.toDomain() }
        val result = dataSource.getMatchesList(0).first()

        Assert.assertEquals(data, result)
    }

    private suspend fun stubGetMatchesList(
        dummyResponse: List<MatchResponse>
    ) {
        whenever(
            webService.getMatchesList(0)

        ).thenReturn(dummyResponse)
    }

    @Test
    fun `getMatchDetails must return MatchDetailsModel when called`() = runBlocking {
        val dummyResponse = MatchDetailsResponse()
        stubGetMatchDetails(dummyResponse)

        val data = dummyResponse.toDomain()
        val result = dataSource.getMatchDetails("").first()

        Assert.assertEquals(data, result)
    }

    private suspend fun stubGetMatchDetails(
        dummyResponse: MatchDetailsResponse
    ) {
        whenever(
            webService.getMatchDetails("")

        ).thenReturn(dummyResponse)
    }
}