@file:JvmName("ContextUtil")

package com.hiden.movies.presentation.util

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Point
import android.media.MediaPlayer
import android.net.ConnectivityManager
import android.os.Build
import androidx.core.content.ContextCompat
import android.view.WindowManager
import timber.log.Timber

@Suppress("DEPRECATION")
    /**
 * Created by edwin on 11/8/17.
 */

fun Context.isConnectionAvailable(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  val networkInfo = connectivityManager.activeNetworkInfo ?: return false
  return networkInfo.isConnected && networkInfo.isAvailable
}

fun Context.getAppName(): String {
  val packageManager = packageManager
  var applicationInfo: ApplicationInfo? = null
  try {
    applicationInfo = packageManager.getApplicationInfo(getApplicationInfo().packageName, 0)
  } catch (e: PackageManager.NameNotFoundException) {
  }
  return if (applicationInfo != null)
    packageManager.getApplicationLabel(applicationInfo) as String
  else "app"
}

fun Context.getScreenHeight(): Int {
  val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
  val display = windowManager.defaultDisplay
  val size = Point()
  display.getSize(size)
  return size.y
}

fun Context.getScreenWidth(): Int {
  val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
  val display = windowManager.defaultDisplay
  val size = Point()
  display.getSize(size)
  return size.x
}

fun Context.pixelsToSp(px: Float): Float {
  val scaledDensity = resources.displayMetrics.scaledDensity
  return px / scaledDensity
}

fun Context.playSoundEffect(id: Int) {
  val mediaPlayer = MediaPlayer.create(this, id)
  if (mediaPlayer == null) {
    Timber.e("Error playing sound; Failed to create MediaPlayer instance.")
    return
  }
  try {
    mediaPlayer.setOnCompletionListener {
      mediaPlayer.reset()
      mediaPlayer.release()
    }
    mediaPlayer.start()
  } catch (e: Exception) {
    Timber.e("Error playing sound; %s.", e.message)
  }
}

fun makeRestartActivityTask(componentName: ComponentName): Intent {
  val intent = Intent(Intent.ACTION_MAIN)
  intent.component = componentName
  intent.addCategory(Intent.CATEGORY_LAUNCHER)
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
  return intent
}

fun Context.pxToDp(px: Float): Float = px / resources.displayMetrics.density

fun Context.dpToPx(dp: Float): Float = dp * resources.displayMetrics.density

fun isMarshmallowOrMore(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

inline fun Context.isPermissionGranted(
  permission: String,
  consumeBlock: () -> Unit
) {
  if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
    consumeBlock.invoke()
  }
}

inline fun Context.isPermissionNotGranted(
  permission: String,
  consumeBlock: () -> Unit
) {
  if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
    consumeBlock.invoke()
  }
}


