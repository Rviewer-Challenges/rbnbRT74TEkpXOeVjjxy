package com.rumosoft.library_components.infrastructure

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.net.toUri

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.openUrl(url: String) =
    Intent(Intent.ACTION_VIEW, url.toUri()).also {
        ContextCompat.startActivity(this, it, null)
    }
