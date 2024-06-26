package com.dubproductions.dequeproject

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.dubproductions.dequeproject.characters.presentation.nav.NavigationHost
import com.dubproductions.dequeproject.characters.presentation.theme.DequeProjectTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppContent() {
    DequeProjectTheme {

        val navController = rememberNavController()

           Surface(
               modifier = Modifier
                   .fillMaxSize(),
               color = MaterialTheme.colorScheme.surface
           ) {
               NavigationHost(navController)
           }
        }
}