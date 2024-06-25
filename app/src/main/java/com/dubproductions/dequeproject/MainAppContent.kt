package com.dubproductions.dequeproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dubproductions.dequeproject.characters.presentation.nav.NavigationHost
import com.dubproductions.dequeproject.characters.presentation.theme.DequeProjectTheme

@Composable
fun MainAppContent() {
    DequeProjectTheme {

        val navController = rememberNavController()

        Scaffold(
            topBar = {

            }
        ) {
           Surface(
               modifier = Modifier
                   .fillMaxSize()
                   .padding(it)
           ) {
               NavigationHost(navController)
           }
        }
    }
}