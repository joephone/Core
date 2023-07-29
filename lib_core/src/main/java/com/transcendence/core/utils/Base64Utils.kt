package com.transcendence.core.utils

import android.util.Base64
import java.nio.charset.StandardCharsets

/**
 * @author joephone
 * @date 2023/5/15
 * @desc
 */

object Base64Utils {



    fun decodeToString(src: ByteArray?): String {
        val bytes = decodeToBytes(src)
        return String(bytes, StandardCharsets.UTF_8)
    }

    fun decodeToBytes(src: String?): ByteArray {
        val bytes = src?.toByteArray(StandardCharsets.UTF_8)
        return decodeToBytes(bytes)
    }

    fun decodeToBytes(src: ByteArray?): ByteArray {
        if (src == null || src.isEmpty()) {
            return ByteArray(0)
        }
        return Base64.decode(src, Base64.NO_WRAP)
    }
}