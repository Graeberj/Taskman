package com.graeberj.taskman.auth.domain.util

interface JsonSerializer {
    fun <T> fromJson(json: String, type: Class<T>): T?
    fun <T> toJson(data: T, type: Class<T>): String?
}
