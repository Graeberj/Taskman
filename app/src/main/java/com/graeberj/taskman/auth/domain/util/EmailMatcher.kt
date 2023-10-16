package com.graeberj.taskman.auth.domain.util

interface EmailMatcher {
    fun matches(email: String): Boolean

}