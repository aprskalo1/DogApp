package hr.algebra.dogapp.framework

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T : Activity> Context.startActivity() =
    startActivity(
        Intent(this, T::class.java)
        .apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) })