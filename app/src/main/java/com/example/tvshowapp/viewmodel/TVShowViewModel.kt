package com.example.tvshowapp.viewmodel


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tvshowapp.model.OneTVShow
import com.example.tvshowapp.model.TVShow
import com.example.tvshowapp.model.TVShowApi
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed class TVShowUiState {
    object Loading : TVShowUiState()
    data class Success(val tvShows: List<TVShow>) : TVShowUiState()
    object Error : TVShowUiState()
}
// for Specific TVshow details according to the id
sealed class TVShowDetailUiState() {
    object Loading : TVShowDetailUiState()
    data class Success(val onetvShow: OneTVShow) : TVShowDetailUiState()
    object Error : TVShowDetailUiState()
}

class TVShowViewModel : ViewModel() {
    var uiState by mutableStateOf<TVShowUiState>(TVShowUiState.Loading)
        private set
//for specific TVshow details according to the id
    var tvShowDetailState by mutableStateOf<TVShowDetailUiState>(TVShowDetailUiState.Loading)
        private set

    var selectedTvShowId by mutableStateOf<Int?>(null)
        private set

    fun setSelectedTvShowId(id: String) {
        selectedTvShowId = id.toIntOrNull()
        getTVShowDetails(id) // Fetch details when ID is set
    }


    init {
        getTVShowList()
       // getTVShowDetails(selectedTvShowId)
    }

    private fun getTVShowList() {
        viewModelScope.launch {
            try {
                val response = TVShowApi.getInstance().getTVShows()
                Log.d("TVShowViewModel", "Fetched ${response.tvShows.size} TV shows")
                Log.d("TVShowViewModel", "Fetched ${response.tvShows} ")

                if (response.tvShows.isNotEmpty()) {
                    uiState  = TVShowUiState.Success(response.tvShows)
                } else {
                    uiState = TVShowUiState.Error
                }
            } catch (e: IOException) {
                uiState = TVShowUiState.Error
                println("Network Error: ${e.message}")
            } catch (e: HttpException) {
                uiState = TVShowUiState.Error
                println("HTTP Error: ${e.message}")
            } catch (e: Exception) {
                uiState = TVShowUiState.Error
                println("Unexpected Error: ${e.message}")
            }
        }
    }
private  fun getTVShowDetails(id: String) {
    Log.d("TVShowViewModel", "Fetched details for TV show ID1: $id")
        tvShowDetailState = TVShowDetailUiState.Loading
    Log.d("TVShowViewModel", "Fetched details for TV show ID2: $id")
        viewModelScope.launch {
            try {
                val response = TVShowApi.getInstance().getTVShowDetails(id)
                Log.d("TVShowViewModel", "Fetched details for TV show ID: $id")
                Log.d("TVShowViewModel", "API Response: $response")
                // Check if onetvShow is not null
                if (response.onetvShow != null) {
                    tvShowDetailState = TVShowDetailUiState.Success(response.onetvShow)
                } else {
                    // Handle the case where response.onetvShow is null
                    tvShowDetailState = TVShowDetailUiState.Error
                    Log.e("TVShowViewModel", "No details available for TV show ID: $id")
                }
            } catch (e: IOException) {
                tvShowDetailState = TVShowDetailUiState.Error
                Log.e("TVShowViewModel", "Network Error: ${e.message}")
            } catch (e: HttpException) {
                tvShowDetailState = TVShowDetailUiState.Error
                Log.e("TVShowViewModel", "HTTP Error: ${e.message}")
            } catch (e: Exception) {
                tvShowDetailState = TVShowDetailUiState.Error
                Log.e("TVShowViewModel", "Unexpected Error: ${e.message}")
            }
        }
    }


}

