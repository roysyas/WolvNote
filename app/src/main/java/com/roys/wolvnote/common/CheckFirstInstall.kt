package com.roys.wolvnote.common

import android.content.Context

fun checkFirstInstall(context: Context) {
    val sharedPreferences = context.getSharedPreferences("Apps", Context.MODE_PRIVATE)
    if (!sharedPreferences.getBoolean("is_not_first_install", false)) {
        generateSecretKey()
        with(sharedPreferences.edit()) {
            putBoolean("is_not_first_install", true)
                .apply()
        }
    }
}