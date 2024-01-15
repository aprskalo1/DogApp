package hr.algebra.dogapp.framework

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.preference.PreferenceManager
import com.bumptech.glide.Glide
import hr.algebra.dogapp.R
import hr.algebra.dogapp.model.DogEntity
import hr.algebra.dogapp.model.DogItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

inline fun <reified T : Activity> Context.startActivity() =
    startActivity(
        Intent(this, T::class.java)
            .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })

inline fun <reified T : BroadcastReceiver> Context.sendBroadcast(boolean: Boolean) =
    sendBroadcast(
        Intent(this, T::class.java)
            .apply { putExtra("apiStatusActive", boolean) }
    )

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

fun Context.saveImage(
    context: Context,
    imageUrl: String,
    fileName: String,
    dirPath: String
) {
    GlobalScope.launch {
        try {
            val bitmap = Glide.with(this@saveImage)
                .asBitmap()
                .load(imageUrl)
                .submit()
                .get()

            val dir = File(dirPath)
            if (!dir.exists()) dir.mkdirs()

            val file = File("$dirPath/$fileName")
            val out = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
            out.flush()
            out.close()
            val handler = Handler(Looper.getMainLooper())
            handler.post {
                Toast.makeText(context, getString(R.string.image_saved), Toast.LENGTH_SHORT)
                    .show()
            }
        } catch (e: Exception) {
            Log.e("saveImage", e.message, e)
        }
    }
}

fun Context.setStringPreference(key: String, value: String?) =
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .putString(key, value)
        .apply()

fun Context.getStringPreference(key: String, defaultValue: String? = null): String? =
    PreferenceManager.getDefaultSharedPreferences(this)
        .getString(key, defaultValue)

fun Context.userExists(): Boolean {
    val email = getStringPreference("email")
    return !email.isNullOrBlank()
}

fun Context.clearPreferences() {
    PreferenceManager.getDefaultSharedPreferences(this)
        .edit()
        .clear()
        .apply()
}

fun DogItem.toDogEntity(id: String): DogEntity {
    return DogEntity(
        id,
        message,
        status
    )
}

fun DogEntity.toDogItem(): DogItem {
    return DogItem(
        message,
        status
    )
}

