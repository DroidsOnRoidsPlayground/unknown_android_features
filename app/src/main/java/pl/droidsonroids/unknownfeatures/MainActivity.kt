package pl.droidsonroids.unknownfeatures

import android.accounts.AccountManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Bundle
import android.os.UserManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.viewbinding.BuildConfig
import pl.droidsonroids.unknownfeatures.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        println(BuildConfig.DEBUG)
        TrafficStatistics().dumpStats(this)
        showNotification()
        //println(getString(R.string.text_in_library))
        println(getSystemService(UserManager::class.java).isUserAGoat)
    }

    private fun showNotification() {
        val notificationManager = getSystemService(NotificationManager::class.java)
        with(notificationManager) {
            val channel =
                NotificationChannel("id", "channel", NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = "description"
                    setShowBadge(true)
                }
            createNotificationChannel(channel)
            val notification = Notification.Builder(applicationContext, channel.id)
                .setCategory("category")
                .setContentTitle("title")
                .setColor(Color.YELLOW)
                .setChronometerCountDown(true)
                .setColorized(true)
                .setNumber(7)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
            notify(1, notification)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}