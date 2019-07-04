@file:JvmName("TypedArrayUtil")

package com.hiden.movies.presentation.util

import android.content.res.TypedArray


inline fun <T : TypedArray?> T.use(block: (T) -> Unit) = try {
  block(this)
} finally {
  this?.recycle()
}