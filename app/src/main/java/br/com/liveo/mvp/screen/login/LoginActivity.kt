package br.com.liveo.mvp.screen.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by rudsonlima on 9/1/17.
 */

class LoginActivity : AppCompatActivity(), LoginContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun onLoading(isLoading: Boolean) {

    }

    override fun onError(error: String?) {

    }

    override val email: String
        get() = "rudsonlive@gmail.com"
    override val password: String
        get() = "123456"


    override fun onLoginSuccess() {

    }

    override fun onLoginFailed(exception: Throwable) {

    }
}
