package com.example.tvshowapp.ui

//required imports
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.tvshowapp.R
import com.example.tvshowapp.model.TVShow
import com.example.tvshowapp.ui.theme.DarkYellow
import com.example.tvshowapp.ui.theme.Grey
import com.example.tvshowapp.ui.theme.LightBlue
import com.example.tvshowapp.ui.theme.LightYellow
import com.example.tvshowapp.viewmodel.TVShowUiState
@Composable
fun InforScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 96.dp) // Apply top padding to the Box to adjust its position
            .background(LightBlue),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Heading at the top center
            Text(
                text = stringResource(R.string.information_screen),
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                color = DarkYellow,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Centered Image below the heading
            Image(
                painter = painterResource(id = R.drawable.indu), // Replace with your image resource
                contentDescription = stringResource(R.string.information_image),
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Text below the image, aligned to the left
            Text(
                text = "I'm Indunil Maheshika Liyanage. I designed and developed this mobile app for\n" +
                        "the Mobile Programming with Native Technology course at Oulu University of\n" +
                        "Applied Sciences. I have used free APIs, specifically https://www.episodate.com/api/.\n" +
                        "This app is about popular TV shows. You can click on a TV show to get more information\n" +
                        "about it.",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                color = LightYellow,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
