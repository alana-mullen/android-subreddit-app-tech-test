package co.uk.thewirelessguy.androidsubreddit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.uk.thewirelessguy.androidsubreddit.model.AndroidSubreddit
import co.uk.thewirelessguy.androidsubreddit.model.State
import co.uk.thewirelessguy.androidsubreddit.repository.RedditRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RedditViewModel @Inject constructor(
    private val repository: RedditRepository
) : ViewModel() {

    // Encapsulate access to mutable LiveData using backing property:
    private val _redditLiveData = MutableLiveData<State<AndroidSubreddit>>()
    val redditLiveData: LiveData<State<AndroidSubreddit>> = _redditLiveData

    fun getRedditLiveData(after: Int? = null) {
        viewModelScope.launch {
            val results = repository.fetchData(after)
            _redditLiveData.postValue(results)
        }
    }
}