@file:Suppress("DEPRECATION", "unused")

package gr.jvoyatz.gamebook.libraries.utils.android

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Looper
import timber.log.Timber


fun Context.isConnected() = kotlin.run {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo = connectivityManager.activeNetworkInfo
     netInfo != null && netInfo.isConnected
}

val Context.isOnline: Boolean
    get() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        return cm?.activeNetwork?.let { net ->
            val nc = cm.getNetworkCapabilities(net)
            nc?.let {
                it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || it.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                )
            } ?: false
        } ?: false
    }


/**
 * Check if current thread is Main Thread.
 */
inline val isMainThread: Boolean get() = Looper.myLooper() == Looper.getMainLooper()

/**
 * Get class name for a particular class
 */
val <T: Any> T.classTag: String
    get() = this.javaClass.simpleName

fun logThread() {
    Timber.d("Current Thread is ${Thread.currentThread()}")
}