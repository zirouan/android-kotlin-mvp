package br.com.liveo.mvp.data.source.local

import android.content.Context
import android.preference.PreferenceManager
import java.lang.ref.WeakReference

class PreferencesHelper(private val mContext: WeakReference<Context>) {

    private fun putString(key: String, value: String) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get())
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun getString(key: String): String {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get())
        return sharedPreferences.getString(key, null)
    }
}
