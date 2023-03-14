package com.adiliqbal.finize.common.extensions

import kotlin.experimental.ExperimentalTypeInference
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

inline fun <T> withScope(dispatcher: CoroutineDispatcher, crossinline block: suspend () -> T) =
    CoroutineScope(dispatcher).launch { block() }

suspend inline fun <T> runOnMainThread(crossinline block: suspend () -> T) =
    withContext(Dispatchers.Main) { block() }

@OptIn(ExperimentalTypeInference::class)
inline fun <T> channelFlowWithAwait(
    @BuilderInference crossinline block: suspend ProducerScope<T>.() -> Unit
) = callbackFlow {
    block(this)
    awaitClose()
}
