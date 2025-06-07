package com.roomdbwithjsondata.utility

import android.content.Context
import androidx.annotation.RawRes

fun Context.readRawResource(@RawRes resId: Int): String {
    return resources.openRawResource(resId).bufferedReader().use { it.readText() }
}
