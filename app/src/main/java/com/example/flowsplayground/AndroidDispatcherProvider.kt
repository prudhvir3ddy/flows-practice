package com.example.flowsplayground

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatcherProvider {
    fun default(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
    fun mainImmediate(): CoroutineDispatcher
}

internal object AndroidDispatcherProvider : DispatcherProvider {

    override fun default(): CoroutineDispatcher = Dispatchers.Default

    override fun io(): CoroutineDispatcher = Dispatchers.IO

    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun mainImmediate(): CoroutineDispatcher = Dispatchers.Main.immediate
}

