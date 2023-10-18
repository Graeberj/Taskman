package com.graeberj.taskman.auth.domain.usecase

data class ValidateFormUseCase(
    val validateEmail: ValidateEmailUseCase,
    val validatePassword: ValidatePasswordUseCase,
    val validateFullName: ValidateFullNameUseCase
)
