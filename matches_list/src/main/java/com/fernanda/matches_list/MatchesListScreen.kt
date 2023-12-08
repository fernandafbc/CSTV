package com.fernanda.matches_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.fernanda.domain.model.MatchStatus
import com.fernanda.uikit.R
import com.fernanda.uikit.components.AppScaffold
import com.fernanda.uikit.components.MatchCard
import com.fernanda.uikit.theme.Silver
import com.fernanda.uikit.theme.StormGray
import com.fernanda.uikit.theme.Typography
import org.koin.androidx.compose.getViewModel

@Composable
fun MatchesListScreen(
    viewModel: MatchesListViewModel = getViewModel()
) {
    LaunchedEffect(key1 = true, block = {
        viewModel.getMatchesList()
    })
    AppScaffold {
        val matchesList = viewModel.matchesList.collectAsLazyPagingItems()
        val isLoading = matchesList.loadState.refresh is LoadState.Loading

        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Silver,
                    backgroundColor = StormGray
                )
            }
        } else {
            LazyColumn(
                Modifier
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

                items(matchesList) { matchModel ->
                    MatchCard(
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }) {
                                viewModel.goToMatchDetails(
                                    matchId = matchModel?.id ?: 0,
                                    matchLeague = matchModel?.league?.name.orEmpty() + matchModel?.serie?.fullName.orEmpty(),
                                    matchDate = matchModel?.beginAt.orEmpty()
                                )
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
    }
}