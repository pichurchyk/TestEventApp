package com.pichurchyk.testeventapp.utils

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visibleIf(isVisible: Boolean) {
    if (isVisible) {
        this.visible()
    } else {
        this.gone()
    }
}
