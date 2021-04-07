package co.uk.thewirelessguy.androidsubreddit.extension

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun Context.openUrl(url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent : CustomTabsIntent = builder.build();
    customTabsIntent.launchUrl(this, Uri.parse(url))
}