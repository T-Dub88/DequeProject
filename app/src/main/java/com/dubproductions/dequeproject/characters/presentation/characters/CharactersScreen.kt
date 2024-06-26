package com.dubproductions.dequeproject.characters.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.dubproductions.dequeproject.characters.domain.model.CharacterImage
import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary
import com.dubproductions.dequeproject.characters.domain.model.Game
import com.dubproductions.dequeproject.characters.domain.network.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    networkState: NetworkResult,
    onCardClicked: (id: String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Text("Characters List")
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            when (networkState) {
                is NetworkResult.Error -> {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Network loading error icon.",
                        Modifier.size(75.dp)
                    )
                    networkState.errorMessage?.let { message ->
                        Text(
                            text = message,
                            fontSize = 20.sp
                        )
                    }
                }
                is NetworkResult.Loading -> {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onSurface,
                    )
                }
                is NetworkResult.Success -> {
                    networkState.data?.let { characterList ->
                        CharacterListDisplay(
                            characterList = characterList,
                            onCardClicked = onCardClicked
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun CharacterListDisplay(
    characterList: List<CharacterSummary>,
    onCardClicked: (id: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(all = 10.dp)
    ) {
        items(characterList) {
            Card(
                modifier = Modifier
                    .padding(vertical = 5.dp),
                onClick = { it.guid?.let { guid -> onCardClicked(guid) } }
            ) {
                Column(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .padding(8.dp)
                ) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .align(Alignment.CenterHorizontally)
                            .width(200.dp),
                        model = it.image?.thumbUrl,
                        contentDescription = "Characters Image",
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }
                    )
                    Text(text = "Name: ${it.name}")
                    Text(text = "Gender: ${getGender(it.gender ?: 0)}")
                    Text(text = "First Game: ${it.firstGame?.name}")
                    Text(text = "Summary: ${it.deck}")
                }
            }
        }
    }
}

private fun getGender(code: Int): String {
    return when(code) {
        1 -> "Male"
        2 -> "Female"
        else -> "Other"
    }
}

@Preview(showBackground = true)
@Composable
fun CharactersScreenPreview() {
    CharactersScreen(
        onCardClicked = {},
        networkState = NetworkResult.Success(
        data = listOf(
            CharacterSummary(
                deck = "This character does stuff",
                firstGame = Game(
                    name = "Legends of this"
                ),
                gender = 1,
                guid = "jfds",
                name = "Tony The Tiger",
                image = CharacterImage(
                    thumbUrl = ""
                )
            ),
            CharacterSummary(
                deck = "This character does stuff",
                firstGame = Game(
                    name = "Legends of this"
                ),
                gender = 1,
                guid = "jfds",
                name = "Tony The Tiger",
                image = CharacterImage(
                    thumbUrl = ""
                )
            ),
            CharacterSummary(
                deck = "This character does stuff",
                firstGame = Game(
                    name = "Legends of this"
                ),
                gender = 1,
                guid = "jfds",
                name = "Tony The Tiger",
                image = CharacterImage(
                    thumbUrl = ""
                )
            )
        )
    )
    )
}
