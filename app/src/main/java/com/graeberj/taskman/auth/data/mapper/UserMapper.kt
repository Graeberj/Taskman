package com.graeberj.taskman.auth.data.mapper

import com.graeberj.taskman.auth.data.remote.dto.LoginResponseDto
import com.graeberj.taskman.auth.domain.models.LoggedInUser

fun LoginResponseDto.toLoginResponse(): LoggedInUser {
    return LoggedInUser(
        token = this.token,
        userId = this.userId,
        fullName = this.fullName
    )
}