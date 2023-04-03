package com.adiliqbal.finize.system

import android.app.NotificationChannel
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.util.ArrayMap
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.toBitmap
import com.adiliqbal.finize.common.extensions.notificationManager
import com.adiliqbal.finize.common.system.NotificationManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class NotificationManagerImpl @Inject constructor(
	@ApplicationContext private val context: Context
) : NotificationManager {

	private data class Notification(
		val id: Int,
		val tag: String? = null,
		val title: String,
		val message: String,
		val image: Bitmap?
	)

	private val notificationsMap: ArrayMap<String, MutableList<Notification>> by lazy { ArrayMap() }

	private fun createChannel(
		id: String,
		name: String,
		description: String,
		importance: Int = android.app.NotificationManager.IMPORTANCE_DEFAULT
	) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			val channel =
				NotificationChannel(id, name, importance).apply { this.description = description }
			context.notificationManager?.createNotificationChannel(channel)
		}
	}

	private fun removeNotifications(tag: String? = null, id: Int? = null) {
		if (id != null) NotificationManagerCompat.from(context).cancel(id)
		else if (tag != null) {
			getActiveNotifications(tag).forEach {
				NotificationManagerCompat.from(context).cancel(tag, it.id)
			}
		}
	}

	private fun getActiveNotifications(tag: String): List<Notification> {
		return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			context.notificationManager?.run {
				activeNotifications.map {
					Notification(
						id = it.id,
						tag = it.tag,
						title = it.notification.extras.getString("android.title", ""),
						message = it.notification.extras.getString("android.text", ""),
						image = it.notification.getLargeIcon()?.loadDrawable(context)?.toBitmap()
					)
				}
			}
		} else {
			notificationsMap[tag]
		}
			?: emptyList()
	}
}
