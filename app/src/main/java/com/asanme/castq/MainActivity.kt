package com.asanme.castq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asanme.castq.data.model.Routes
import com.asanme.castq.view.QueueView
import com.asanme.castq.view.ui.theme.CastQTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CastQTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App()
                }
            }
        }
    }
}

@Composable
private fun App() {
    val navController = rememberNavController()
    NavigationComponent(navController)
}

@Composable
private fun NavigationComponent(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.QueueViewRoute.route
    ) {
        composable(Routes.QueueViewRoute.route) {
            QueueView(navController)
        }
    }
}