package com.fernanda.match_details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.fernanda.domain.model.PlayerModel
import com.fernanda.domain.utils.getDate
import com.fernanda.uikit.R
import com.fernanda.uikit.components.AppScaffold
import com.fernanda.uikit.components.PlayerCard
import com.fernanda.uikit.components.TeamsRow
import com.fernanda.uikit.theme.Silver
import com.fernanda.uikit.theme.StormGray
import com.fernanda.uikit.theme.Typography
import com.fernanda.uikit.utils.FadingAnimation
import com.fernanda.uikit.utils.SlideHorizontalAnimation
import org.koin.androidx.compose.getViewModel

@Composable
fun MatchDetailsScreen(
    matchId: String,
    matchLeague: String,
    matchDate: String,
    viewModel: MatchDetailsViewModel = getViewModel()
) {
    LaunchedEffect(key1 = true, block = {
        viewModel.getMatchDetails(matchId)
    })
    AppScaffold {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp)
            ) {
                Header(
                    matchLeague = matchLeague,
                    onNavigationIconClick = { viewModel.popBackStack() })
                val firstTeam = viewModel.matchDetails.teams.firstOrNull()
                val secondTeam = viewModel.matchDetails.teams.lastOrNull()
                SlideHorizontalAnimation(visible = viewModel.isLoading.not()) {
                    TeamsRow(
                        firstLogo = firstTeam?.image.orEmpty(),
                        firstName = firstTeam?.name.orEmpty(),
                        secondLogo = secondTeam?.image.orEmpty(),
                        secondName = secondTeam?.name.orEmpty()
                    )
                }
                MatchDate(matchDate = matchDate)
                SlideHorizontalAnimation(visible = viewModel.isLoading.not()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        AllPlayers(
                            firstTeamPlayers = firstTeam?.players ?: emptyList(),
                            secondTeamPlayers = secondTeam?.players ?: emptyList()
                        )
                    }
                }
            }
            FadingAnimation(
                visible = viewModel.isLoading,
                modifier = Modifier.align(Alignment.Center)
            ) {
                CircularProgressIndicator(
                    color = Silver,
                    backgroundColor = StormGray
                )
            }
        }
    }
}

@Composable
private fun Header(matchLeague: String, onNavigationIconClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, top = 32.dp, end = 24.dp, bottom = 8.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = stringResource(id = R.string.navigation_icon_description),
            tint = Color.White,
            modifier = Modifier
                .size(24.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) {
                    onNavigationIconClick.invoke()
                }
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = matchLeague,
            style = Typography.titleSmall,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun MatchDate(matchDate: String) {
    Text(
        text = matchDate.getDate(stringResource(id = R.string.today)),
        style = Typography.bodyMedium,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, bottom = 20.dp, start = 24.dp, end = 24.dp)
    )
}

@Composable
private fun AllPlayers(
    firstTeamPlayers: List<PlayerModel>,
    secondTeamPlayers: List<PlayerModel>
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        TeamPlayers(players = firstTeamPlayers, isLeftCard = true, modifier = Modifier.weight(1F))
        TeamPlayers(players = secondTeamPlayers, isLeftCard = false, modifier = Modifier.weight(1F))
    }
}

@Composable
private fun TeamPlayers(players: List<PlayerModel>, isLeftCard: Boolean, modifier: Modifier) {
    Column(modifier = modifier) {
        players.forEach {
            PlayerCard(
                isLeftCard = isLeftCard,
                playerName = it.firstName + " " + it.lastName,
                playerNickname = it.nickname.orEmpty(),
                photo = it.image.orEmpty()
            )
            Spacer(modifier = Modifier.size(12.dp))
        }
    }
}