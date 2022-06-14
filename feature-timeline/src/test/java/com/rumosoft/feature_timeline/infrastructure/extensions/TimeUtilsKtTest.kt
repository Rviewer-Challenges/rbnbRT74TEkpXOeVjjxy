package com.rumosoft.feature_timeline.infrastructure.extensions

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.time.Duration.Companion.parseIsoStringOrNull

internal class TimeUtilsKtTest {
    @Test
    fun checkFormatDuration() {
        assertEquals("03:02", parseIsoStringOrNull("PT3M2S")?.formatDuration())
        assertEquals("10:05", parseIsoStringOrNull("PT10M5S")?.formatDuration())
    }
}

