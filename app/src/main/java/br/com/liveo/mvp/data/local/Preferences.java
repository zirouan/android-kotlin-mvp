package br.com.liveo.mvp.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.lang.ref.WeakReference;

public class Preferences implements PreferencesHelper {

    private WeakReference<Context> mContext;

    public Preferences(WeakReference<Context> context) {
        this.mContext = context;
    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public void clearToken() {

    }

    @Override
    public void saveToken(String token) {

    }

    //region Methods Preferences
    private void putString(String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
        return sharedPreferences.getString(key, null);
    }

    private void putInt(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
        return sharedPreferences.getInt(key, 0);
    }

    private void putBoolean(String key, boolean value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    private boolean getBoolean(String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
        return sharedPreferences.getBoolean(key, false);
    }

    private boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext.get());
        return sharedPreferences.getBoolean(key, defaultValue);
    }
    //endregion
}
