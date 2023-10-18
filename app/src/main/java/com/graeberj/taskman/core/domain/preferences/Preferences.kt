package com.graeberj.taskman.core.domain.preferences

import com.graeberj.taskman.auth.domain.models.LoggedInUser

interface Preferences {
    fun saveToken(token: String)
    fun saveFullName(name: String)
    fun saveUserId(userId: String)
    fun loadUser(): LoggedInUser?
    fun deleteUser()

    companion object {
        const val KEY_TOKEN = "token"
        const val KEY_NAME = "name"
        const val KEY_USERID = "userid"
    }
}