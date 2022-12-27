package com.simplemobiletools.draw.rec.dialogs

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.StringRes
import com.simplemobiletools.draw.rec.R

fun showAlert(context: Context, title: String? = null, message: String? = null) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .show()
}

fun showAlert(context: Context, @StringRes title: Int = 0, @StringRes message: Int = 0) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNeutralButton(R.string.alert_button_ok) { _, _ -> }
        .show()
}
