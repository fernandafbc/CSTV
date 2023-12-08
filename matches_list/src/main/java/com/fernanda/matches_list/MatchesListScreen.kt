package com.fernanda.matches_list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.fernanda.domain.model.LeagueModel
import com.fernanda.domain.model.MatchModel
import com.fernanda.domain.model.MatchStatus
import com.fernanda.domain.model.SerieModel
import com.fernanda.domain.model.TeamDetailsModel
import com.fernanda.domain.model.TeamModel
import com.fernanda.uikit.R
import com.fernanda.uikit.components.AppScaffold
import com.fernanda.uikit.components.MatchCard
import com.fernanda.uikit.theme.Background
import com.fernanda.uikit.theme.Silver
import com.fernanda.uikit.theme.StormGray
import com.fernanda.uikit.theme.Typography
import com.fernanda.uikit.utils.FadingAnimation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MatchesListScreen(
    viewModel: MatchesListViewModel = getViewModel()
) {
    LaunchedEffect(key1 = true, block = {
        viewModel.getMatchesList()
    })
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1_000)
        refreshing = false
        viewModel.getMatchesList()
    }

    val pullState = rememberPullRefreshState(refreshing, ::refresh)
    AppScaffold {
        val matchesList = viewModel.matchesList.collectAsLazyPagingItems()
        val isRefreshLoading = matchesList.loadState.refresh is LoadState.Loading
        val isAppendLoading = matchesList.loadState.append is LoadState.Loading

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                isVisible = isRefreshLoading
            )
            FadingAnimation(visible = isRefreshLoading.not()) {
                MatchesList(matchesList, pullState) { matchModel ->
                    viewModel.goToMatchDetails(
                        matchId = matchModel?.id ?: 0,
                        matchLeague = matchModel?.league?.name.orEmpty() + matchModel?.serie?.fullName.orEmpty(),
                        matchDate = matchModel?.beginAt.orEmpty()
                    )
                }
            }
            ProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                isVisible = isAppendLoading
            )

            PullRefreshIndicator(
                modifier = Modifier.align(Alignment.TopCenter),
                refreshing = refreshing,
                state = pullState,
                contentColor = Silver,
                backgroundColor = StormGray
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MatchesList(
    matchesList: LazyPagingItems<MatchModel>,
    pullState: PullRefreshState,
    onCardClick: (MatchModel?) -> Unit
) {
    LazyColumn(
        Modifier
            .pullRefresh(pullState)
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.matches_list_title),
                style = Typography.titleLarge,
                color = Color.White,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }

        items(matchesList.itemCount) { index ->
            val matchModel = matchesList[index]

            MatchCard(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }) {
                        onCardClick.invoke(matchModel)
                    },
                beginDate = matchModel?.beginAt.orEmpty(),
                firstTeam = matchModel?.teams?.firstOrNull()?.team,
                secondTeam = matchModel?.teams?.lastOrNull()?.team,
                leagueLogo = matchModel?.league?.image.orEmpty(),
                leagueName = matchModel?.league?.name.orEmpty() + matchModel?.serie?.fullName.orEmpty(),
                isNow = matchModel?.status == MatchStatus.RUNNING
            )
        }
    }
}

@Composable
private fun ProgressIndicator(modifier: Modifier, isVisible: Boolean) {
    FadingAnimation(visible = isVisible, modifier = modifier) {
        CircularProgressIndicator(
            color = Silver,
            backgroundColor = StormGray
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview(showBackground = true)
private fun MatchesListScreenPreview() {
    Column(
        modifier = Modifier
            .background(color = Background)
            .fillMaxSize()
    ) {
        val pullState = rememberPullRefreshState(false, onRefresh = {})
        val matchesList = flowOf(
            PagingData.from(
                listOf(
                    MatchModel(
                        beginAt = "2023-12-08T15:04:29Z",
                        league = LeagueModel(name = "League"),
                        serie = SerieModel(fullName = "serie"),
                        teams = listOf(
                            TeamModel(
                                TeamDetailsModel(
                                    name = "Time 1"
                                )
                            ),
                            TeamModel(
                                TeamDetailsModel(
                                    name = "Time 2"
                                )
                            )
                        )
                    ),
                    MatchModel(
                        beginAt = "2023-12-10T15:04:29Z",
                        league = LeagueModel(name = "League"),
                        serie = SerieModel(fullName = "serie"),
                        teams = listOf(
                            TeamModel(
                                TeamDetailsModel(
                                    name = "Time 1"
                                )
                            ),
                            TeamModel(
                                TeamDetailsModel(
                                    name = "Time 2"
                                )
                            )
                        )
                    ),
                    MatchModel(
                        beginAt = "2023-12-20T15:04:29Z",
                        league = LeagueModel(name = "League"),
                        serie = SerieModel(fullName = "serie"),
                        teams = listOf(
                            TeamModel(
                                TeamDetailsModel(
                                    name = "Time 1"
                                )
                            ),
                            TeamModel(
                                TeamDetailsModel(
                                    name = "Time 2"
                                )
                            )
                        )
                    )
                )
            )
        ).collectAsLazyPagingItems()
        MatchesList(matchesList, pullState) {}
    }
}