package co.uk.thewirelessguy.androidsubreddit.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import co.uk.thewirelessguy.androidsubreddit.R
import co.uk.thewirelessguy.androidsubreddit.adapter.RedditListAdapter
import co.uk.thewirelessguy.androidsubreddit.databinding.FragmentListBinding
import co.uk.thewirelessguy.androidsubreddit.extension.openUrl
import co.uk.thewirelessguy.androidsubreddit.extension.setEmptyStateView
import co.uk.thewirelessguy.androidsubreddit.extension.toast
import co.uk.thewirelessguy.androidsubreddit.model.State
import co.uk.thewirelessguy.androidsubreddit.viewmodel.RedditViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class ListFragment : ViewBindingBaseFragment<FragmentListBinding>() {

    private lateinit var commitsAdapter: RedditListAdapter
    private val viewModel: RedditViewModel by viewModels()

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentListBinding
        get() = FragmentListBinding::inflate

    override fun setupView() {
        setHasOptionsMenu(true)

        binding.apply {
            recyclerView.apply {
                // Set LinearLayoutManager using default vertical list:
                layoutManager = LinearLayoutManager(context)

                hasFixedSize() // Improve performance (use only with fixed size items)
                setItemViewCacheSize(20)

                // Set adapter and initialise with empty list:
                commitsAdapter = RedditListAdapter(mutableListOf()) {
                    // Open Reddit post in a web browser using Custom Tabs
                    context.openUrl(resources.getString(R.string.permalink, it.data?.permalink))
                }
                adapter = commitsAdapter

                // Set a view to display when list is empty:
                adapter?.setEmptyStateView(binding.listEmpty.root)
            }
            swipeRefresh.setOnRefreshListener {
                Timber.d("swipeRefresh")
                loadData()
            }
        }

        // Listen for changes from the LiveData ViewModel. Update view when data is loaded.
        viewModel.redditLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is State.Success -> {
                    binding.swipeRefresh.isRefreshing = false
                    commitsAdapter.setData(it.data.data?.children?.toMutableList())
                }
                is State.Error -> {
                    binding.swipeRefresh.isRefreshing = false
                    context?.toast("Error: ${it.message}")
                }
                is State.Loading -> {
                    Timber.d("Loading...")
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.d("onResume")
        loadData()
    }

    private fun loadData() {
        Timber.d("Getting data...")
        viewModel.getRedditLiveData()
    }
}