package com.shishir.takeNotesV2.util

object ObjectUtils {
    fun isNotNull(value: String): Boolean {
        if(value != null && !value.isEmpty())
            return true
        return false
    }
}