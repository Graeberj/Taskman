package com.graeberj.taskman.auth.data.util

import android.util.Patterns
import com.graeberj.taskman.auth.domain.util.EmailMatcher

class EmailMatcherImpl : EmailMatcher {
    override fun matches(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}