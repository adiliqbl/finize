package com.adiliqbal.finize.common.extensions

import android.content.Context
import android.widget.Toast

val Context.notificationManager
	get() = (getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager?)

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
	Toast.makeText(this, message, duration).show()
}
