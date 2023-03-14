package com.adiliqbal.finize.common.system

interface Logger {

    fun setup(release: Boolean)

    fun d(message: String)
    fun d(t: Throwable, message: String? = null)

    fun i(message: String)
    fun i(t: Throwable, message: String? = null)

    fun w(message: String)
    fun w(t: Throwable, message: String? = null)

    fun e(message: String, t: Throwable? = null)
    fun e(t: Throwable)
}
