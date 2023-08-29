package com.example.videoexoplayer.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(private val context: Context) {
    companion object {
        private const val USER_TOKEN = "user_token"
        private const val FIRST_RUN_APP = "FIRST_RUN_APP"
    }


    private fun getPref(context: Context): SharedPreferences {
        //  return PreferenceManager.getDefaultSharedPreferences(context);
        return context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        )
    }

    fun setToken(token: String) {
        val editor: SharedPreferences.Editor = getPref(context)
            .edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String {
        return getPref(context).getString(
            USER_TOKEN, ""
        )!!
    }

    fun setFirstRun(isFirst: Boolean) {
        val editor: SharedPreferences.Editor = getPref(context).edit()
        editor.putBoolean(FIRST_RUN_APP, isFirst)
        editor.apply()
    }

    fun getFirstRun(): Boolean {
        return getPref(context).getBoolean(
            FIRST_RUN_APP, true
        )
    }

}