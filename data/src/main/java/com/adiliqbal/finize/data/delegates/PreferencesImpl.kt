package com.adiliqbal.finize.data.delegates

import com.adiliqbal.finize.datastore.AppPreferences
import javax.inject.Inject

class PreferencesImpl @Inject constructor(private val appPreferences: AppPreferences) : Preferences
