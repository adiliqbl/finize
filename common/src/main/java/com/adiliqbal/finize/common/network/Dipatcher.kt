package com.adiliqbal.finize.common.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val dispatcher: Thread)

enum class Thread {
    IO,
    MAIN
}
