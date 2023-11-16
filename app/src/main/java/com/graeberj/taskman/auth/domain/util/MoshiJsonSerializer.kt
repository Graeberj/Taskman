package com.graeberj.taskman.auth.domain.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject

class MoshiJsonSerializer @Inject constructor(
    private val moshi: Moshi
) : JsonSerializer {

    override fun <T> fromJson(json: String, type: Class<T>): T? {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(type)
        return jsonAdapter.fromJson(json)
    }

    override fun <T> toJson(data: T, type: Class<T>): String? {
        val jsonAdapter: JsonAdapter<T> = moshi.adapter(type)
        return jsonAdapter.toJson(data)
    }
}