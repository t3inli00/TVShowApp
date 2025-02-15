package com.example.tvshowapp.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tvshowapp.ui.theme.TVShowAppTheme
import com.example.tvshowapp.viewmodel.TVShowViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.tvshowapp.R
import com.example.tvshowapp.viewmodel.TVShowDetailUiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TVShowAppTheme {
                TVShowApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVShowApp(tvShowViewModel: TVShowViewModel = viewModel()) {
    val navController = rememberNavController()
    // Track the current route
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route


    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.tv_show_most_popular),
                        color = MaterialTheme.colorScheme.onPrimary
                    )

                },
                navigationIcon = {
                    if (currentRoute == stringResource(R.string.informationscreen) || currentRoute?.startsWith(
                            stringResource(R.string.tvshowdetails_tvshowid)) == true) {
                        // Show back button when on the Information or Details screen
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ChevronLeft, // Back arrow
                                contentDescription = stringResource(R.string.back_button),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                actions = {
                    if (currentRoute == stringResource(R.string.tvshowlist)) {
                        // Show info icon only on TVShowScreen
                        IconButton(onClick = { navController.navigate("informationScreen") }) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = stringResource(R.string.information_icon),
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    )
    { innerPadding ->
       // TVShowScreen(modifier = Modifier.padding(innerPadding),tvShowViewModel.uiState)
        NavHost(navController, startDestination = "tvShowList") {
            composable("tvShowList") {
                TVShowScreen(modifier = Modifier.padding(innerPadding),navController, tvShowViewModel.uiState)
            }
            composable("tvShowDetails/{tvShowId}") { backStackEntry ->
                val tvShowId = backStackEntry.arguments?.getString("tvShowId")?.toIntOrNull() ?: -1
                Log.d("MainActivity", "Press TV show ID: $tvShowId")

                TVShowDetailsScreen(modifier = Modifier.padding(innerPadding), navController, tvShowViewModel, tvShowId.toString())
            }

            composable("informationScreen") {
                InforScreen() // Display the Information Screen when the route is hit
            }
        }
    }
}
