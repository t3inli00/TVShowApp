package com.example.tvshowapp.ui

//required imports
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tvshowapp.model.TVShow
import com.example.tvshowapp.ui.theme.DarkYellow
import com.example.tvshowapp.ui.theme.Grey
import com.example.tvshowapp.ui.theme.LightYellow
import com.example.tvshowapp.viewmodel.TVShowUiState

@Composable
    fun TVShowScreen(modifier: Modifier = Modifier,navController: NavController, uiState: TVShowUiState) {

        when (uiState) {
            is TVShowUiState.Loading -> LoadingScreen()
            is TVShowUiState.Success -> TVShowList(navController, uiState.tvShows)
            is TVShowUiState.Error -> ErrorScreen()
        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TVShowList(navController: NavController, tvShows: List<TVShow>) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 96.dp) // Apply top padding to the Box instead of LazyColumn
        ){
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, end = 8.dp))
            {
                items(tvShows) { tvShow ->

                    // Wrap each item inside a Card to give it a nice design
                    val interactionSource = remember { MutableInteractionSource() }
                    val isHovered by interactionSource.collectIsHoveredAsState()

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .hoverable(interactionSource) // Detects hover events
                            .clickable {
                                navController.navigate("tvShowDetails/${tvShow.id}")
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = if (isHovered) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            else MaterialTheme.colorScheme.surface
                        ),
                        shape = MaterialTheme.shapes.medium
                    )  {
                        Row(modifier = Modifier.padding(16.dp)) {
                            // Display the image using AsyncImage from Coil
                            AsyncImage(
                                model = tvShow.thumbnail, // This should be image URL
                                contentDescription = tvShow.name,
                                modifier = Modifier
                                    .size(80.dp) // Adjust the size of the image
                                    .padding(end = 16.dp)
                                    .clip(RoundedCornerShape(12.dp)) // Apply rounded corners
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp)) // Add shadow effect
                                    .background(MaterialTheme.colorScheme.surface),
                                contentScale = ContentScale.Crop
                            )

                            // Display the title of the TV show
                            Column(modifier = Modifier.fillMaxSize()) {
                                Text(
                                    text = tvShow.name.toString(),
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.titleMedium,
                                    //modifier = Modifier.padding(bottom = 8.dp)
                                )

                                // Optionally display more information
                                Text(
                                    text = "${tvShow.network}",
                                    color = DarkYellow,
                                    style = MaterialTheme.typography.titleSmall,
                                    //modifier = Modifier.padding(bottom = 4.dp)
                                )

                                Text(
                                    text = "Started On: ${tvShow.startDate}",
                                    color = Grey,
                                    style = MaterialTheme.typography.titleSmall,
                                    //modifier = Modifier.padding(bottom = 8.dp)
                                )
                                Text(
                                    text = "${tvShow.status}",
                                    color = LightYellow,
                                    style = MaterialTheme.typography.titleSmall,
                                    // modifier = Modifier.padding(bottom = 8.dp)
                                )
                            }
                        }

                        // Divider between items
                        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                    }
                }
            }}
    }



