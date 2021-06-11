package pl.droidsonroids.unknownfeatures

import android.app.usage.NetworkStats
import android.app.usage.NetworkStatsManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.TrafficStats

class TrafficStatistics {

    init {
        println(TrafficStats.getMobileTxBytes())
        println(TrafficStats.getTotalTxBytes())
    }

    fun dumpStats(context:Context) {
        val networkStatsManager = context.getSystemService(NetworkStatsManager::class.java)
        val bucket = NetworkStats.Bucket()
        networkStatsManager.querySummary(ConnectivityManager.TYPE_WIFI, null, 0, Long.MAX_VALUE).getNextBucket(bucket)
        println(bucket.rxBytes)
    }
}