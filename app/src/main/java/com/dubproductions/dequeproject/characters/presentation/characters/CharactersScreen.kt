package com.dubproductions.dequeproject.characters.presentation.characters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.dubproductions.dequeproject.characters.domain.model.CharacterSummary
import com.dubproductions.dequeproject.characters.presentation.utils.GenderCodes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(
    screenState: CharactersScreenState,
    onCardClicked: (id: String) -> Unit,
    loadNewPage: () -> Unit
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

            if (screenState.isLoadingInitialData) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onSurface,
                )
            } else if (screenState.errorMessage != null) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Network loading error icon.",
                    Modifier.size(75.dp)
                )
                Text(
                    text = screenState.errorMessage,
                    fontSize = 20.sp
                )

            } else {
                CharacterListDisplay(
                    characterList = screenState.characterList,
                    onCardClicked = onCardClicked,
                    screenState = screenState,
                    loadNewPage = loadNewPage
                )
            }
        }
    }

}

@Composable
fun CharacterListDisplay(
    characterList: List<CharacterSummary>,
    onCardClicked: (id: String) -> Unit,
    screenState: CharactersScreenState,
    loadNewPage: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(all = 10.dp)
    ) {
        items(characterList.size) { i ->

            if (i >= characterList.size - 1 && !screenState.endReached && !screenState.isLoadingNewPage) {
                loadNewPage()
            }

            Card(
                modifier = Modifier
                    .padding(vertical = 5.dp),
                onClick = { characterList[i].guid?.let { guid -> onCardClicked(guid) } }
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
                        model = characterList[i].image?.thumbUrl,
                        contentDescription = "Character's Image",
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(30.dp)
                            )
                        }
                    )
                    Text(text = "Name: ${characterList[i].name}")
                    Text(text = "Gender: ${GenderCodes.getGender(characterList[i].gender ?: 0)}")
                    Text(text = "First Game: ${characterList[i].firstGame?.name}")
                    Text(text = "Summary: ${characterList[i].deck}")
                }
            }
        }

        item {
            if (screenState.isLoadingNewPage) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}
