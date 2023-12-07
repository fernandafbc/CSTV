package com.fernanda.data.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fernanda.data.MatchDataSource
import com.fernanda.domain.model.MatchModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest

class MatchPagingSource(
    private val dataSource: MatchDataSource
) : PagingSource<Int, MatchModel>() {
    private val initialPageIndex: Int = 1
    override fun getRefreshKey(state: PagingState<Int, MatchModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MatchModel> {
        var response: LoadResult<Int, MatchModel> = LoadResult.Error(Throwable())
        return coroutineScope {
            val currentPage = params.key ?: initialPageIndex
            val result = async {
                try {
                    dataSource.getMatchesList(page = currentPage).collectLatest {
                        response = LoadResult.Page(
                            data = it,
                            prevKey = if (currentPage == initialPageIndex) null else currentPage.minus(
                                1
                            ),
                            nextKey = if (it.isEmpty()) null else currentPage.plus(1)
                        )
                    }
                } catch (exception: Exception) {
                    response = LoadResult.Error(exception)
                }
            }
            result.await()
            return@coroutineScope response
        }
    }
}