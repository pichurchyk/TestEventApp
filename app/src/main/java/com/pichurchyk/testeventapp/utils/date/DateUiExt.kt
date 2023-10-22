package com.pichurchyk.testeventapp.utils.date

import com.pichurchyk.testeventapp.R
import com.pichurchyk.testeventapp.domain.entity.date.DateType

fun DateType.getStringResId(): Int {
    return when (this) {
        DateType.TODAY -> R.string.label_today
        DateType.YESTERDAY -> R.string.label_yesterday
    }
}
