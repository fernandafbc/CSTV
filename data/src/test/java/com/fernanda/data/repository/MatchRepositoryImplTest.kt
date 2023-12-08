package com.fernanda.data.repository

import com.fernanda.data.MatchDataSource
import com.fernanda.domain.model.MatchDetailsModel
import com.fernanda.domain.model.MatchModel
import com.fernanda.domain.repository.MatchRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class MatchRepositoryImplTest {
    @Mock
    private lateinit var dataSource: MatchDataSource
    private lateinit var repository: MatchRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = MatchRepositoryImpl(dataSource)
    }

    @Test
    fun `getMatchesList must return list MatchModel when called`() = runBlocking {
        val result = listOf(MatchModel())
        stubGetMatchesList(result)

        val data = dataSource.getMatchesList(1)
        data.collectLatest {
            Assert.assertEquals(result, it)
        }
    }

    private fun stubGetMatchesList(
        model: List<MatchModel>
    ) {
        whenever(dataSource.getMatchesList(1)).thenReturn(
            flowOf(model)
        )
    }

    @Test
    fun `getMatchDetails must return MatchDetailsModel when called`() = runBlocking {
        val result = MatchDetailsModel()
        stubGetMatchDetails(result)

        val data = repository.getMatchDetails("")
        data.collectLatest {
            Assert.assertEquals(result, it)
        }
    }

    private fun stubGetMatchDetails(
        model: MatchDetailsModel
    ) {
        whenever(dataSource.getMatchDetails("")).thenReturn(
            flowOf(model)
        )
    }
}