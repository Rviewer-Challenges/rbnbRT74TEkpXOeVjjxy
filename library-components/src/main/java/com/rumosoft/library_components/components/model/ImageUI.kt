package com.rumosoft.library_components.components.model

import com.rumosoft.library_components.components.model.ImageTypeUI.Static

data class ImageUI(
    val id: Long,
    val url: String,
    val time: String? = null,
    val imageType: ImageTypeUI = Static,
)

enum class ImageTypeUI {
    Static, Gif, Video,
}
