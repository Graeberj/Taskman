package com.graeberj.taskman.auth.domain.util

import android.content.Context
import androidx.annotation.StringRes
import com.graeberj.taskman.R

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    data object UnknownError : UiText()
    class StringResource(@StringRes val resId: Int, vararg val args: Any) : UiText()

    fun getString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is UnknownError -> context.getString(R.string.an_unknown_error_occurred)
            is StringResource -> context.getString(resId, *args)
        }
    }
}