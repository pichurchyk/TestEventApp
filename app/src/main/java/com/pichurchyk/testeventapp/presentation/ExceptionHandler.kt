package com.pichurchyk.testeventapp.presentation

import android.util.Log

data class ExceptionHandler(
    val exception: Throwable,
) {
    init {
        Log.e(LOG_TAG, exception.message ?: "Exception", exception)
    }

    companion object {
        private const val LOG_TAG = "ExceptionHandler"
    }
}
