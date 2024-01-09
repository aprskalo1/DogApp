package hr.algebra.dogapp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hr.algebra.dogapp.framework.callDelayed
import hr.algebra.dogapp.framework.startActivity

class DogReceiver : BroadcastReceiver() {
    private val apiStatusActive: Boolean
        get() {
            return Intent().getBooleanExtra("apiStatusActive", false)
        }

    override fun onReceive(context: Context, intent: Intent) {
        if (apiStatusActive) {
            if (context is SplashScreenActivity) {
                callDelayed(3000L) {
                    context.finish()
                }
            }
        } else {
            context.startActivity<HostActivity>()
        }
    }
}