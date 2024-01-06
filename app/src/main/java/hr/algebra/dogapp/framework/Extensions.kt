package hr.algebra.dogapp.framework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Handler
import android.os.Looper
import androidx.core.content.getSystemService
import androidx.preference.PreferenceManager

inline fun <reified T : Activity> Context.startActivity() =
    startActivity(
        Intent(this, T::class.java)
            .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

fun callDelayed(delay: Long, work: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed(
        work,
        delay
    )
}

fun Context.isOnline(): Boolean {
    val cm = getSystemService<ConnectivityManager>()

    cm?.activeNetwork.let { network ->
        cm?.getNetworkCapabilities(network)?.run {
            return when {
                hasTransport(TRANSPORT_WIFI) -> true
                hasTransport(TRANSPORT_CELLULAR) -> true
                else -> false
            }
        }
        return false
    }
}

//this has to be changed since it will be used for storing logged in user
fun Context.setBooleanPreference(key: String, value: Boolean = true) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putBoolean(key, value)
        .apply()

fun Context.getBooleanPreference(key: String, defaultValue: Boolean = false) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getBoolean(key, defaultValue)