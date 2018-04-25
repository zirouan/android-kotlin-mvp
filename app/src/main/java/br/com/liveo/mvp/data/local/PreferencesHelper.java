package br.com.liveo.mvp.data.local;

/**
 * Created by rudsonlima on 10/2/17.
 */

public interface PreferencesHelper {

    String getToken();
    void clearToken();
    void saveToken(String token);
}
