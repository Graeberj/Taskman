package com.graeberj.taskman.auth.domain.usecase

import com.graeberj.taskman.auth.domain.util.EmailMatcher

class ValidateEmailUseCase(
    private val emailMatcher: EmailMatcher
) {
    operator fun invoke(email: String): Boolean {
        return emailMatcher.matches(email)
    }
}
