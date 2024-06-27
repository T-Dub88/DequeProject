package com.dubproductions.dequeproject.characters.presentation.details

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.dubproductions.dequeproject.R
import com.dubproductions.dequeproject.characters.data.model.CharacterDetails
import com.dubproductions.dequeproject.characters.domain.model.CharacterImage
import com.dubproductions.dequeproject.characters.domain.model.Game
import com.dubproductions.dequeproject.characters.domain.network.ScreenState
import com.dubproductions.dequeproject.characters.presentation.utils.GenderCodes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    networkState: ScreenState<CharacterDetails>,
    onBackPressed: () -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.details_title))
                },
                scrollBehavior = scrollBehavior,
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button)
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (networkState) {
                is ScreenState.Error -> {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = stringResource(id = R.string.network_error_description),
                        Modifier.size(75.dp)
                    )
                    networkState.errorMessage?.let { message ->
                        Text(
                            text = message,
                            fontSize = 20.sp
                        )
                    }
                }
                is ScreenState.Loading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                is ScreenState.Success -> {
                    networkState.data?.let { details ->
                        CharacterDetailsDisplay(details = details)
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterDetailsDisplay(details: CharacterDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .align(Alignment.CenterHorizontally)
                .padding(8.dp),
            model = details.image?.thumbUrl,
            contentDescription = stringResource(id = R.string.character_image_description),
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(30.dp)
                )
            }
        )

        Text(
            text = stringResource(
                id = R.string.character_name,
                details.name ?: stringResource(id = R.string.unknown)
            )
        )

        Text(
            text = stringResource(
                id = R.string.last_name,
                details.lastName ?: stringResource(id = R.string.unknown)
            )
        )

        Text(
            text = stringResource(
                id = R.string.aliases,
                details.aliases ?: stringResource(id = R.string.unknown)
            )
        )

        Text(
            text = stringResource(
                id = R.string.real_name,
                details.realName ?: stringResource(id = R.string.unknown)
            )
        )

        Text(
            text = stringResource(
                id = R.string.gender,
                GenderCodes.getGender(details.gender ?: 0)
            )
        )

        Text(
            text = stringResource(
                id = R.string.birthday,
                details.birthday ?: stringResource(id = R.string.unknown)
            )
        )

        Text(
            text = stringResource(
                id = R.string.first_game,
                details.firstGame?.name ?: stringResource(id = R.string.unknown)
            )
        )

        Text(
            text = stringResource(
                id = R.string.summary,
                details.deck ?: stringResource(id = R.string.unknown)
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(
        networkState = ScreenState.Success(
            data = CharacterDetails(
                aliases = "Hylia",
                birthday = "1-1-86",
                deck = "This is a character.",
                firstGame = Game("Zelda 1"),
                games = listOf(
                    Game("Zelda 1"),
                    Game("Zelda 2")
                ),
                gender = 2,
                guid = "",
                image = CharacterImage("someurl"),
                lastName = "Hyrule",
                name = "Zelda",
                realName = "Princess Zelda of Hyrule",
                siteDetailUrl = "website_link"
            )
        )
    ) {

    }
}
