package com.dubproductions.dequeproject.characters.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

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

    }
}

fun NavGraphBuilder.detailsScreen() {
    composable<NavRoutes.DetailsScreen> {
        val args = it.toRoute<NavRoutes.DetailsScreen>()
    }
}
