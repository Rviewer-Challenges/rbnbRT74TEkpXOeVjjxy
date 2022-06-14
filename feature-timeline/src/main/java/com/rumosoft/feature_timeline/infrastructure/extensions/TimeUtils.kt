package com.rumosoft.feature_timeline.infrastructure.extensions

import kotlin.time.Duration

internal fun Duration.formatDuration(): String {
    val seconds = this.inWholeSeconds
    val hours = seconds / 3600
    val minutes = seconds % 3600 / 60
    val secs = seconds % 60
    return if (hours > 0) {
        String.format("%02d:%02d:%02d", hours, minutes, secs)
    } else {
        String.format("%02d:%02d", minutes, secs)
    }
}
