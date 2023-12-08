package com.fernanda.uikit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.fernanda.uikit.theme.Background
import com.fernanda.uikit.theme.CardColor
import com.fernanda.uikit.theme.Silver
import com.fernanda.uikit.theme.StormGray
import com.fernanda.uikit.theme.Typography

@Composable
fun PlayerCard(
    modifier: Modifier = Modifier,
    isLeftCard: Boolean,
    playerName: String,
    playerNickname: String,
    photo: String
) {
    val playerCardModel = if (isLeftCard) PlayerCardModel() else PlayerCardModel(
        nameAlignment = Alignment.BottomEnd,
        photoAlignment = Alignment.TopStart,
        textAlign = TextAlign.Start,
        roundedCornerShape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp),
        startPadding = 80.dp,
        endPadding = 8.dp
    )
    Box(
        modifier = modifier.height(58.dp)
    ) {
        PlayerName(
            modifier = Modifier.align(playerCardModel.nameAlignment),
            roundedCornerShape = playerCardModel.roundedCornerShape,
            startPadding = playerCardModel.startPadding,
            endPadding = playerCardModel.endPadding,
            textAlign = playerCardModel.textAlign,
            name = playerName,
            nickname = playerNickname
        )
        PlayerPhoto(modifier = Modifier.align(playerCardModel.photoAlignment), photo = photo)
    }
}

@Composable
private fun PlayerName(
    modifier: Modifier = Modifier,
    roundedCornerShape: RoundedCornerShape,
    startPadding: Dp,
    endPadding: Dp,
    textAlign: TextAlign,
    name: String,
    nickname: String
) {
    Column(
        modifier = modifier
            .heightIn(min = 54.dp)
            .fillMaxWidth()
            .background(
                color = CardColor,
                shape = roundedCornerShape
            ),
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = nickname,
            style = Typography.bodyLarge,
            color = Color.White,
            textAlign = textAlign,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = startPadding, end = endPadding)
                .fillMaxWidth()
        )
        Text(
            text = name,
            style = Typography.labelLarge,
            color = StormGray,
            textAlign = textAlign,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(bottom = 8.dp, start = startPadding, end = endPadding)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun PlayerPhoto(
    modifier: Modifier = Modifier,
    photo: String
) {
    SubcomposeAsyncImage(
        model = photo,
        contentDescription = null,
        modifier = modifier
            .height(58.dp)
            .padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
            .width(48.dp)
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .background(color = Silver, shape = RoundedCornerShape(8.dp))
            )
        } else
            SubcomposeAsyncImageContent()
    }
}

data class PlayerCardModel(
    val nameAlignment: Alignment = Alignment.BottomStart,
    val textAlign: TextAlign = TextAlign.End,
    val photoAlignment: Alignment = Alignment.TopEnd,
    val roundedCornerShape: RoundedCornerShape = RoundedCornerShape(
        topEnd = 12.dp,
        bottomEnd = 12.dp
    ),
    val startPadding: Dp = 8.dp,
    val endPadding: Dp = 80.dp
)

@Composable
@Preview(showBackground = true)
private fun PlayerCardPreview() {
    Column(
        modifier = Modifier
            .background(color = Background)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(13.dp)
        ) {
            PlayerCard(
                modifier = Modifier.weight(1F),
                isLeftCard = true,
                playerName = "Nome Jogador",
                playerNickname = "Nickname",
                photo = ""
            )
            PlayerCard(
                modifier = Modifier.weight(1F),
                isLeftCard = false,
                playerName = "Nome Jogador",
                playerNickname = "Nickname",
                photo = ""
            )
        }
    }
}