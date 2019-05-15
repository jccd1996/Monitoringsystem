package com.jccd.monitoringsystem.db.prefs

import android.content.Context
import android.content.SharedPreferences
import com.jccd.monitoringsystem.db.model.User
import com.jccd.monitoringsystem.utils.Constants

class SessionManager(private var context: Context, private val name : String? = null) {

    private val SHARED_PREFERENCES_NAME = "UserPrefs"
    private val USER_NAME = "user_name"
    private val USER_EMAIL = "user_email"
    private val USER_UID = "user_uid"
    private val USER_PROFILE = "user_profile"

    private val sharedPreferencesUser: SharedPreferences by lazy {
        context.getSharedPreferences(name?: javaClass.simpleName, Context.MODE_PRIVATE)
    }

    fun saveUserLogin(user: User) {
        val editor = sharedPreferencesUser.edit()
        editor.putString(USER_NAME, user.fullName)
        editor.putString(USER_EMAIL, user.email)
        editor.putString(USER_UID, user.uid)
        editor.apply()
    }

    fun logOut(){
        val editor = sharedPreferencesUser.edit()
        editor.clear()
        editor.apply()
    }

    fun getUserName(): String = sharedPreferencesUser.getString(USER_NAME,Constants.EMPTY)
    fun getUserEmail():String = sharedPreferencesUser.getString(USER_EMAIL, Constants.EMPTY)
}