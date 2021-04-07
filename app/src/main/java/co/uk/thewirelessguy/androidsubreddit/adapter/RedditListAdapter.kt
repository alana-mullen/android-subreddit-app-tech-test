package co.uk.thewirelessguy.androidsubreddit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.uk.thewirelessguy.androidsubreddit.R
import co.uk.thewirelessguy.androidsubreddit.databinding.ListItemBinding
import co.uk.thewirelessguy.androidsubreddit.extension.loadUrl
import co.uk.thewirelessguy.androidsubreddit.model.AndroidSubreddit

class RedditListAdapter(
    private var items: MutableList<AndroidSubreddit.Data.Children>,
    private val listener: (AndroidSubreddit.Data.Children) -> Unit
) : RecyclerView.Adapter<RedditListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(items[position]) {
                binding.apply {
                    postTitle.text = data?.title
                    author.text = binding.root.context.resources.getString(R.string.user, data?.author)
                    thumbnail.loadUrl(data?.thumbnail.toString())
                }
                holder.itemView.setOnClickListener {
                    listener(this)
                }
            }
        }
    }

    override fun getItemCount() = items.size

    internal fun setData(data: MutableList<AndroidSubreddit.Data.Children>?) {
        // Assign the list to the RecyclerView. If data is null, assign an empty list to the adapter.
        this.items = data ?: mutableListOf()
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)
}