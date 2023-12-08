package com.fernanda.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.fernanda.domain.model.TeamDetailsModel
import com.fernanda.domain.utils.getDate
import com.fernanda.uikit.R
import com.fernanda.uikit.theme.Alabaster30
import com.fernanda.uikit.theme.Background
import com.fernanda.uikit.theme.CardColor
import com.fernanda.uikit.theme.Red
import com.fernanda.uikit.theme.Silver
import com.fernanda.uikit.theme.Typography
import com.fernanda.uikit.theme.White20
import com.fernanda.uikit.theme.White50

@Composable
fun MatchCard(
    modifier: Modifier = Modifier,
    beginDate: String,
    firstTeam: TeamDetailsModel?,
    secondTeam: TeamDetailsModel?,
    leagueLogo: String,
    leagueName: String,
    isNow: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = CardColor, shape = RoundedCornerShape(size = 16.dp))
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .background(
                    color = if (isNow) Red else Alabaster30,
                    RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
                )
        ) {
            Text(
                text = if (isNow) stringResource(id = R.string.date_now) else beginDate.getDate(
                    stringResource(id = R.string.today)
                ),
                style = Typography.bodySmall,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
        TeamsRow(
            firstLogo = firstTeam?.image.orEmpty(),
            firstName = firstTeam?.name.orEmpty(),
            secondLogo = secondTeam?.image.orEmpty(),
            secondName = secondTeam?.name.orEmpty()
        )
        Divider(
            color = White20
        )
        LeagueSeries(logo = leagueLogo, league = leagueName)
    }
}

@Composable
fun TeamsRow(
    firstLogo: String,
    firstName: String,
    secondLogo: String,
    secondName: String
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        TeamInfo(logo = firstLogo, name = firstName)
        Text(
            text = stringResource(id = R.string.vs),
            style = Typography.labelLarge,
            color = White50,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        TeamInfo(logo = secondLogo, name = secondName)
    }
}

@Composable
private fun TeamInfo(
    logo: String,
    name: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            model = logo,
            contentDescription = stringResource(id = R.string.team_logo_description)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                PlaceholderCircle(size = 60.dp)
            } else
                SubcomposeAsyncImageContent(modifier = Modifier.size(60.dp))
        }
        Text(
            text = name,
            style = Typography.labelMedium,
            color = Color.White,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

@Composable
private fun LeagueSeries(
    logo: String,
    league: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        SubcomposeAsyncImage(
            model = logo,
            contentDescription = stringResource(id = R.string.league_logo_description),
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            val state = painter.state
            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                PlaceholderCircle(size = 16.dp)
            } else
                SubcomposeAsyncImageContent(modifier = Modifier.size(16.dp))
        }
        Text(
            text = league,
            style = Typography.labelSmall,
            color = Color.White
        )
    }
}

@Composable
private fun PlaceholderCircle(
    size: Dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .background(color = Silver, shape = CircleShape)
    )
}

@Composable
@Preview(showBackground = true)
private fun MatchCardPreview() {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .background(color = Background)
                .fillMaxSize()
        ) {
            MatchCard(
                modifier = Modifier.padding(16.dp),
                beginDate = "",
                firstTeam = TeamDetailsModel(0, "", "Time 1"),
                secondTeam = TeamDetailsModel(1, "", "Time 2"),
                leagueLogo = "",
                leagueName = "Liga + serie",
                isNow = true
            )
            MatchCard(
                modifier = Modifier.padding(16.dp),
                beginDate = "22.04, 22:00",
                firstTeam = TeamDetailsModel(0, "", "Time 1"),
                secondTeam = TeamDetailsModel(1, "", "Time 2"),
                leagueLogo = "",
                leagueName = "Liga + serie",
                isNow = false
            )
        }
    }
}