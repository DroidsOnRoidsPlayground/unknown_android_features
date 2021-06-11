package pl.droidsonroids.unknownfeatures

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemoteService : Service() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(intent: Intent): IBinder {
        // Return the interface
        return binder
    }


    private val binder = object : IRemoteService.Stub() {
        override fun getPid(): Int {
            return 123456
        }

        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String
        ) {
            // Does nothing
        }
    }
}