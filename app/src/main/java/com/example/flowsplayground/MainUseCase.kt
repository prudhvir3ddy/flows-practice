package com.example.flowsplayground

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainUseCase : UseCase {
    override operator fun invoke() = flow {
        (1..10).forEach {
            emit(it)
        }
    }
}

interface UseCase {
    operator fun invoke(): Flow<Int>
}