package com.fernanda.domain.usecase

import androidx.paging.PagingData
import com.fernanda.domain.model.MatchModel
import com.fernanda.domain.repository.MatchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever

class GetMatchesListUseCaseTest {
    @Mock
    private lateinit var response: PagingData<MatchModel>
    @Mock
    private lateinit var repository: MatchRepository
    private lateinit var useCase: GetMatchesListUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetMatchesListUseCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @Test
    fun `When succeed must return PagingData of MatchModel`() = runBlocking {
        stubOnSuccess()
        useCase.run().collectLatest {
            Assert.assertEquals(response, it)
        }
    }

    private fun stubOnSuccess() {
        whenever(
            repository.getMatchesList()
        ).thenReturn(flowOf(response))
    }
}