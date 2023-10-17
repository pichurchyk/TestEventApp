package com.pichurchyk.testeventapp.data

import com.pichurchyk.testeventapp.presentation.ExceptionHandler

sealed class Resource<T : Any> {
    data class Success<T : Any>(val data: T) : Resource<T>()
    data class Error<T : Any>(val e: ExceptionHandler) : Resource<T>()
}
