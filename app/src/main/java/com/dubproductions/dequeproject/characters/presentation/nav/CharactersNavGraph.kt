package com.dubproductions.dequeproject.characters.presentation.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dubproductions.dequeproject.characters.presentation.characters.CharactersScreen
import com.dubproductions.dequeproject.characters.presentation.characters.CharactersViewModel
import com.dubproductions.dequeproject.characters.presentation.details.DetailsScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.CharactersScreen
    ) {
        charactersScreen(navController)
        detailsScreen(navController)
    }
}

fun NavGraphBuilder.charactersScreen(
    navController: NavHostController
) {
    composable<NavRoutes.CharactersScreen> {
        val charactersViewModel = hiltViewModel<CharactersViewModel>()
        val networkState by charactersViewModel.networkDataState.collectAsStateWithLifecycle()
        CharactersScreen(
            networkState = networkState,
            onCardClicked = {
                navController.navigate(NavRoutes.DetailsScreen(it))
            }
        )
    }
}

fun NavGraphBuilder.detailsScreen(
    navController: NavHostController
) {
    composable<NavRoutes.DetailsScreen> {
        val args = it.toRoute<NavRoutes.DetailsScreen>()
        DetailsScreen(
            onBackPressed = {
                navController.popBackStack()
            }
        )
    }
}
