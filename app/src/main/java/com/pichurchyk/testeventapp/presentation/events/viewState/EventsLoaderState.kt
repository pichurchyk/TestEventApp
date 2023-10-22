package com.pichurchyk.testeventapp.presentation.events.viewState

open class Loader {
    open var isVisible: Boolean = false
}

class LoaderBig() : Loader() {
    override var isVisible: Boolean = false
}

class LoaderSmall() : Loader() {
    override var isVisible: Boolean = false
}
