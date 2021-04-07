package co.uk.thewirelessguy.androidsubreddit.network

import co.uk.thewirelessguy.androidsubreddit.model.AndroidSubreddit
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("hot.json?limit=10")
    suspend fun getData(
        @Query("after") after: Int? = null
    ): AndroidSubreddit
}