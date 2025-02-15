package com.example.tvshowapp.model

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class Success(
    @SerializedName("tv_shows") val tvShows: List<TVShow>
)
//for specific TV show by providing the id
data class Successone(
    @SerializedName("tvShow") val onetvShow: OneTVShow
)

data class TVShow(
    @SerializedName("id") val id: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("start_date") val startDate: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("network") val network: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("image_thumbnail_path") val thumbnail: String? = null,
    @SerializedName("description") val description: String? = null
)

//for specific TV show by providing the id
data class OneTVShow(
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("start_date") val startDate: String? = null,
    @SerializedName("country") val country: String? = null,
    @SerializedName("network") val network: String? = null,
    @SerializedName("status") val status: String? = null,
    @SerializedName("image_thumbnail_path") val thumbnail: String? = null,
    @SerializedName("description") val description: String? = null
)


//interface for get data
interface TVShowApi {
    @GET("most-popular")
    suspend fun getTVShows(): Success  // Ensure it returns Success, not List<TVShow>

    @GET("show-details")
    suspend fun getTVShowDetails(@Query("q") tvShowId: String): Successone

        companion object {
        private var TVShowService: TVShowApi? = null
        private const val BASE_URL = "https://www.episodate.com/api/"

        fun getInstance(): TVShowApi {
            if (TVShowService == null) {
                TVShowService = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(TVShowApi::class.java)
            }
            return TVShowService!!
        }
    }
}

