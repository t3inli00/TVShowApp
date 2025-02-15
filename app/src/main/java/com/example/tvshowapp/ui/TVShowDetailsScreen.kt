package com.example.tvshowapp.ui


//required imports
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tvshowapp.model.OneTVShow
import com.example.tvshowapp.model.TVShow
import com.example.tvshowapp.ui.theme.DarkYellow
import com.example.tvshowapp.ui.theme.Grey
import com.example.tvshowapp.ui.theme.LightYellow
import com.example.tvshowapp.viewmodel.TVShowDetailUiState
import com.example.tvshowapp.viewmodel.TVShowUiState
import com.example.tvshowapp.viewmodel.TVShowViewModel
@Composable
fun TVShowDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: TVShowViewModel,
    tvShowId: String
) {
    Log.d("TVShowDetailScreen", "tvShowId: $tvShowId")

    // Set the selected TV show ID when this screen is launched
    LaunchedEffect(tvShowId) {
        viewModel.setSelectedTvShowId(tvShowId)
    }

    when (viewModel.tvShowDetailState) {
        is TVShowDetailUiState.Loading -> LoadingScreen()
        is TVShowDetailUiState.Success -> {
            val show = (viewModel.tvShowDetailState as TVShowDetailUiState.Success).onetvShow
            TVShowDetail(navController, show)
        }
        is TVShowDetailUiState.Error -> ErrorScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TVShowDetail(navController: NavController, oneTVShow: OneTVShow) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
    ) {
        LazyColumn( // Use LazyColumn for scrollable content
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column {
                        Row( // Use Row to align image and text horizontally
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            // Image on the left
                            AsyncImage(
                                model = oneTVShow.thumbnail,
                                contentDescription = oneTVShow.name,
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
                                    .background(MaterialTheme.colorScheme.surface),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(4.dp)) // Space between image and text

                            // Text details on the right
                            Column(
                                modifier = Modifier
                                    .weight(1f) // Ensures text takes up remaining space
                            ) {
                                Text(
                                    text = oneTVShow.name,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    style = MaterialTheme.typography.titleLarge
                                )

                                Text(
                                    text = "Network: ${oneTVShow.network}",
                                    color = DarkYellow,
                                    style = MaterialTheme.typography.titleSmall
                                )

                                Text(
                                    text = "Started On: ${oneTVShow.startDate}",
                                    color = Grey,
                                    style = MaterialTheme.typography.titleSmall
                                )

                                Text(
                                    text = "Status: ${oneTVShow.status}",
                                    color = LightYellow,
                                    style = MaterialTheme.typography.titleSmall
                                )
                            }
                        }

                        // Description placed below the Row, spanning full width
                        Text(
                            text = "${oneTVShow.description}",
                            color = DarkYellow,
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}

