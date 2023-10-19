package com.pichurchyk.testeventapp.presentation.video

import android.content.Context
import android.view.KeyEvent
import android.widget.MediaController
import androidx.navigation.findNavController

class CustomMediaController(context: Context) : MediaController(context) {

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            findNavController().popBackStack()
            return true
        }
        return super.dispatchKeyEvent(event)
    }
}
