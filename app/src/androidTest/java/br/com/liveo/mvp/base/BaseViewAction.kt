package br.com.liveo.mvp.base

import android.support.test.espresso.ViewAction
import android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom
import android.view.View
import org.hamcrest.Matcher

/**
 * Created by Everdes Soares on 10/8/2017.
 */

abstract class BaseViewAction : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return isAssignableFrom(View::class.java)
    }

    override fun getDescription(): String {
        return "View is not type " + View::class.java
    }
}
