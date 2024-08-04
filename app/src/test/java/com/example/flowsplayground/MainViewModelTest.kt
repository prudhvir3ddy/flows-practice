package com.example.flowsplayground

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MainViewModelTest {

    @get:Rule
    val testCoroutineRule = MainCoroutineRule()

    val mainViewModel = MainViewModel(object: UseCase {
        override fun invoke(): Flow<Int> {
            throw Exception()
        }

    }, TestDispatcherProvider())


    @Test
    fun testError() = runTest {
        mainViewModel.test()
        assertEquals(true, mainViewModel.isError)
    }
}

class TestDispatcherProvider(
    val dispatcher: TestDispatcher = StandardTestDispatcher(),
) : DispatcherProvider {

    override fun default(): CoroutineDispatcher = dispatcher

    override fun io(): CoroutineDispatcher = dispatcher

    override fun main(): CoroutineDispatcher = dispatcher

    override fun mainImmediate(): CoroutineDispatcher = dispatcher
}

@OptIn(ExperimentalCoroutinesApi::class)
class MainCoroutineRule(
    val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
) : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}