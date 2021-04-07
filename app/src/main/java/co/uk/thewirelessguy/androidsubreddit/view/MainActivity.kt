package co.uk.thewirelessguy.androidsubreddit.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import co.uk.thewirelessguy.androidsubreddit.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()
}