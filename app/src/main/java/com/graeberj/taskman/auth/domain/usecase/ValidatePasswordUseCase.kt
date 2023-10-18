package com.graeberj.taskman.auth.domain.usecase

class ValidatePasswordUseCase {
    operator fun invoke(password: String): Boolean {
        return password.length >= 9 &&
                password.any { it.isLowerCase() } &&
                password.any { it.isUpperCase() } &&
                password.any { it.isDigit() }
    }
}
