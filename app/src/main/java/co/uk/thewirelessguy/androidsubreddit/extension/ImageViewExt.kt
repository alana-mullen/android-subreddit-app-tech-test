package co.uk.thewirelessguy.androidsubreddit.extension

import android.widget.ImageView
import co.uk.thewirelessguy.androidsubreddit.R
import coil.load

/**
 * Wraps the image loading library with an extension to simplify image loading and to make it
 * easier to swap out libraries at a later date.
 */

fun ImageView.loadUrl(url: String) {
    this.load(url) {
        placeholder(R.drawable.ic_launcher_foreground)
        error(R.drawable.ic_launcher_foreground)
    }
}