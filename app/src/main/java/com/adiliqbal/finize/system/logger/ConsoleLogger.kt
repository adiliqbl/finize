package com.adiliqbal.finize.system.logger

import android.annotation.SuppressLint
import android.util.Log
import com.adiliqbal.finize.common.system.Logger
import javax.inject.Inject
import javax.inject.Singleton
import timber.log.Timber

@Singleton
internal class ConsoleLogger @Inject constructor() : Logger {

    override fun setup(release: Boolean) {
        Timber.plant(if (release) Timber.DebugTree() else ConsoleLoggingTree())
    }

    override fun d(message: String) = Timber.d(message)

    override fun d(t: Throwable, message: String?) = Timber.d(t, message)

    override fun i(message: String) = Timber.i(message)

    override fun i(t: Throwable, message: String?) = Timber.i(t, message)

    override fun w(message: String) = Timber.w(message)

    override fun w(t: Throwable, message: String?) = Timber.w(t, message)

    override fun e(message: String, t: Throwable?) = Timber.e(message, t)

    override fun e(t: Throwable) = Timber.e(t)

    private class ConsoleLoggingTree : Timber.Tree() {

        @SuppressLint("LogNotTimber")
        override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) return
            Log.e(tag, message, t ?: Exception(message))
        }
    }
}
