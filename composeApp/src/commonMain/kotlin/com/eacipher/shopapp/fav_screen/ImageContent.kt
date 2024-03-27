package com.eacipher.shopapp.fav_screen

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.model.ImageAction
import com.seiko.imageloader.rememberImagePainter
import com.seiko.imageloader.rememberImageSuccessPainter
import com.seiko.imageloader.ui.AutoSizeBox
import com.seiko.imageloader.ui.AutoSizeImage

@Composable
fun ImageContent() {
    CompositionLocalProvider(
     //   LocalImageLoader provides remember { generateImageLoader() },
    ) {
        // Option 1 on 1.7.0+
        AutoSizeImage(
            "https://...",
            contentDescription = "image",
        )
        // Option 2 on 1.7.0+
        AutoSizeBox("https://...") { action ->
            when (action) {
                is ImageAction.Success -> {
                    Image(
                        rememberImageSuccessPainter(action),
                        contentDescription = "image",
                    )
                }
                is ImageAction.Loading -> {}
                is ImageAction.Failure -> {}
            }
        }
        // Option 3
        Image(
            painter = rememberImagePainter("https://.."),
            contentDescription = "image",
        )
    }
}