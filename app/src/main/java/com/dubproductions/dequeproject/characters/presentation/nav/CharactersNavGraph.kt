package com.dubproductions.dequeproject.characters.presentation.nav

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dubproductions.dequeproject.characters.presentation.characters.CharactersScreen
import com.dubproductions.dequeproject.characters.presentation.characters.CharactersViewModel

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.CharactersScreen
    ) {
        charactersScreen(navController)
        detailsScreen()
    }
}

fun NavGraphBuilder.charactersScreen(
    navController: NavHostController
) {
    composable<NavRoutes.CharactersScreen> {
        val charactersViewModel = hiltViewModel<CharactersViewModel>()

        CharactersScreen(

        )
    }
}

fun NavGraphBuilder.detailsScreen() {
    composable<NavRoutes.DetailsScreen> {
        val args = it.toRoute<NavRoutes.DetailsScreen>()
    }
}
