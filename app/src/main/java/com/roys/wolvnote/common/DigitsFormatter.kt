package com.roys.wolvnote.common

fun digitsFormater(input: String): String{
    var result = ""
    val digits = input.filter { it.isDigit() }
    if (digits.isNotEmpty()) {
        val formatted = digits.toLongOrNull()?.let {
            "%,d".format(it)
        } ?: ""
        result = formatted
    }

    return result
}