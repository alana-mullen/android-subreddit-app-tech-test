package co.uk.thewirelessguy.androidsubreddit.repository

import co.uk.thewirelessguy.androidsubreddit.model.AndroidSubreddit
import co.uk.thewirelessguy.androidsubreddit.model.State
import co.uk.thewirelessguy.androidsubreddit.network.ApiInterface
import co.uk.thewirelessguy.androidsubreddit.network.ResponseHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RedditRepository @Inject constructor(
    private val client: ApiInterface
) {

    suspend fun fetchData(after: Int? = null): State<AndroidSubreddit> {
        val responseHandler = ResponseHandler()

        return withContext(Dispatchers.IO) {
            try {
                val response = client.getData(after)
                return@withContext responseHandler.handleSuccess(response)
            } catch (e: Exception) {
                return@withContext responseHandler.handleException(e)
            }
        }
    }
}