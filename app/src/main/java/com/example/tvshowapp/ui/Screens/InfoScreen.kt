package com.example.tvshowapp.ui.Screens

//required imports
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tvshowapp.R
import com.example.tvshowapp.ui.theme.DarkYellow
import com.example.tvshowapp.ui.theme.LightBlue
import com.example.tvshowapp.ui.theme.LightYellow

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
                text = stringResource(R.string.i_m_indunil_maheshika_liyanage_i_designed_and_developed_this_mobile_app_for) +
                        stringResource(R.string.the_mobile_programming_with_native_technology_course_at_oulu_university_of) +
                        stringResource(R.string.applied_sciences_i_have_used_free_apis_specifically_https_www_episodate_com_api) +
                        stringResource(R.string.this_app_is_about_popular_tv_shows_you_can_click_on_a_tv_show_to_get_more_information) +
                        stringResource(R.string.about_it),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                color = LightYellow,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
