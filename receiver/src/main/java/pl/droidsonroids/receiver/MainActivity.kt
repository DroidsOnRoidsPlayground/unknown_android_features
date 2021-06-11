package pl.droidsonroids.receiver

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import pl.droidsonroids.unknownfeatures.IRemoteService

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent().setClassName("pl.droidsonroids.unknownfeatures", "RemoteService")
        bindService(intent, RemoteServiceConnection(), BIND_AUTO_CREATE)
    }

    inner class RemoteServiceConnection :ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val asInterface: IRemoteService = IRemoteService.Stub.asInterface(service)
            println(asInterface.pid)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            //TODO
        }

    }
}