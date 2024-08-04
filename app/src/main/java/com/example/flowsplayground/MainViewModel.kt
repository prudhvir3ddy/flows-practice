package com.example.flowsplayground

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel(
    private val mainUseCase: UseCase,
    private val dispatcher: DispatcherProvider
) : ViewModel() {

    var isError = false

    fun test() {
        mainUseCase.invoke()
            .flowOn(dispatcher.default())
            .catch {
                isError = true
            }
            .onEach {
                //No op
            }
            .launchIn(viewModelScope)

    }
}
