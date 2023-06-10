package com.postliu.openai.base

fun Map<*, *>.navigationParamsGet(): String {
    return "?".plus(entries.joinToString("&") {
        "${it.key}=${it.value}"
    })
}

fun Map<*, *>.navigationParamsPut(): String {
    return "?".plus(entries.joinToString("&") {
        "${it.key}={${it.value}}"
    })
}