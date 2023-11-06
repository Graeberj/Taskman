package com.graeberj.taskman.core.data.preferences

import android.content.SharedPreferences
import com.graeberj.taskman.auth.domain.models.LoggedInUser
import com.graeberj.taskman.core.domain.preferences.Preferences

class DefaultPreferences(
    private val sharedPreferences: SharedPreferences
) : Preferences {
    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(Preferences.KEY_TOKEN, token).apply()
    }

    override fun saveFullName(name: String) {
        sharedPreferences.edit().putString(Preferences.KEY_NAME, name).apply()
    }

    override fun saveUserId(userId: String) {
        sharedPreferences.edit().putString(Preferences.KEY_USERID, userId).apply()
    }

    override fun loadUser(): LoggedInUser? {
        val token = sharedPreferences.getString(Preferences.KEY_TOKEN, "")
        val userid = sharedPreferences.getString(Preferences.KEY_USERID, "")
        val fullName = sharedPreferences.getString(Preferences.KEY_NAME, "")
        if (token == null || userid == null || fullName == null) {
            return null
        }
        return LoggedInUser(
            token = token,
            userId = userid,
            fullName = fullName
        )
    }

    override fun deleteUser() {
        sharedPreferences.edit().remove(Preferences.KEY_TOKEN).remove(Preferences.KEY_NAME)
            .remove(Preferences.KEY_USERID).apply()
    }
}