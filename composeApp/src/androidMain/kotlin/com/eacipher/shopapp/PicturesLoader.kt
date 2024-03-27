package com.eacipher.shopapp

import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.component.setupDefaultComponents
import com.seiko.imageloader.option.androidContext
import okio.Path.Companion.toOkioPath

class PicturesLoader {
//    fun generateImageLoader(): ImageLoader {
//        return ImageLoader {
//            options {
//                androidContext(AndroidApp.INSTANCE)
//            }
//            components {
//                setupDefaultComponents()
//            }
//            interceptor {
//                // cache 25% memory bitmap
//                bitmapMemoryCacheConfig {
//                    maxSizePercent(AndroidApp.INSTANCE, 0.25)
//                }
//                // cache 50 image
//                imageMemoryCacheConfig {
//                    maxSize(50)
//                }
//                // cache 50 painter
//                painterMemoryCacheConfig {
//                    maxSize(50)
//                }
//                diskCacheConfig {
//                    directory(AndroidApp.INSTANCE.cacheDir.resolve("image_cache").toOkioPath())
//                    maxSizeBytes(512L * 1024 * 1024) // 512MB
//                }
//            }
//        }
//    }
}