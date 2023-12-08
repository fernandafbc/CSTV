package com.fernanda.domain.usecase

import com.fernanda.domain.model.MatchDetailsModel
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

class GetMatchDetailsUseCaseTest {
    @Mock
    private lateinit var response: MatchDetailsModel

    @Mock
    private lateinit var repository: MatchRepository
    private lateinit var useCase: GetMatchDetailsUseCase

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetMatchDetailsUseCase(
            scope = CoroutineScope(Dispatchers.Unconfined),
            repository
        )
    }

    @Test
    fun `When succeed must return MatchDetailsModel`() = runBlocking {
        stubOnSuccess()
        useCase.run(
            params = ""
        ).collectLatest {
            Assert.assertEquals(response, it)
        }
    }

    private fun stubOnSuccess() {
        whenever(
            repository.getMatchDetails("")
        ).thenReturn(flowOf(response))
    }
}