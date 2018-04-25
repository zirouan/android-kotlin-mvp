package br.com.liveo.mvp.screen

import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.annotation.StringRes
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.RootMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.uiautomator.UiDevice
import android.support.v7.widget.AppCompatImageButton
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageButton
import br.com.liveo.mvp.R
import br.com.liveo.mvp.di.modules.HelperTestModule
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import java.lang.ref.WeakReference

/**
 * Created by rudsonlima on 10/3/17.
 */

abstract class BaseActivityTestRule {

    var context: WeakReference<Context>? = null

    protected var helperTestModule: HelperTestModule? = null

    private val intent: Intent
        get() = Intent()

    protected abstract val activityTestRule: ActivityTestRule<*>

    protected fun onInitHelperTestModule() {
        context = WeakReference(InstrumentationRegistry.getTargetContext())

        helperTestModule = HelperTestModule(context)

        helperTestModule?.let {
            ApplicationTestComponentRule.module(it)
        }

        activityTestRule.launchActivity(intent)
    }

    protected fun assertToast(@StringRes stringResource: Int) {
        onView(withText(stringResource)).inRoot(RootMatchers.withDecorView(
                not(`is`(activityTestRule.activity.window.decorView)))).check(matches(isDisplayed()))
    }

    protected fun performAction(@IdRes resourceId: Int, viewAction: ViewAction) {
        onView(withId(resourceId)).perform(viewAction)
    }

    protected fun performClick(resourceId: Int) {
        onView(withId(resourceId)).perform(click())
    }

    protected fun performTextClick(resourceId: Int) {
        onView(allOf(withText(resourceId), isDisplayed())).perform(click())
    }

    protected fun performClick(resourceId: Int, resourceIdwithParent: Int) {
        onView(allOf(withId(resourceId),
                withParent(withId(resourceIdwithParent)),
                isDisplayed())).perform(click())
    }

    protected fun performClick(resourceId: Int, resourceIdwithParentAllOf: Int, resourceIdwithParent: Int) {
        onView(allOf(withId(resourceId),
                withParent(allOf(withId(resourceIdwithParentAllOf),
                        withParent(withId(resourceIdwithParent)))),
                isDisplayed())).perform(click())
    }

    protected fun performItemClick(@IdRes resourceId: Int) {
        onView(allOf(withId(resourceId),
                isDisplayed()))
                .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    protected fun performSwipeLeft(resourceId: Int) {
        onView(withId(resourceId)).perform(swipeLeft())
    }

    protected fun performSwipeLeft(resourceId: Int, resourceIdwithParent: Int) {
        onView(allOf(withId(resourceId),
                withParent(withId(resourceIdwithParent)),
                isDisplayed())).perform(swipeLeft())
    }

    protected fun performSwipeRight(resourceId: Int) {
        onView(withId(resourceId)).perform(swipeRight())
    }

    protected fun performSwipeRight(resourceId: Int, resourceIdwithParent: Int) {
        onView(allOf(withId(resourceId),
                withParent(withId(resourceIdwithParent)),
                isDisplayed())).perform(swipeRight())
    }

    protected fun performSwipeUp(resourceId: Int) {
        onView(withId(resourceId)).perform(swipeUp())
    }

    protected fun performSwipeUp(resourceId: Int, resourceIdwithParent: Int) {
        onView(allOf(withId(resourceId),
                withParent(withId(resourceIdwithParent)),
                isDisplayed())).perform(swipeUp())
    }

    protected fun performSwipeDown(resourceId: Int) {
        onView(withId(resourceId)).perform(swipeDown())
    }

    protected fun performSwipeDown(resourceId: Int, resourceIdwithParent: Int) {
        onView(allOf(withId(resourceId),
                withParent(withId(resourceIdwithParent)),
                isDisplayed())).perform(swipeDown())
    }

    protected fun performClickImeAction(resourceId: Int) {
        onView(withId(resourceId)).perform(pressImeActionButton())
    }

    protected fun performClickImeActionScrollTo(resourceId: Int) {
        onView(withId(resourceId)).perform(scrollTo(), pressImeActionButton())
    }

    protected fun performTypeText(resourceId: Int, text: String) {
        onView(withId(resourceId)).perform(typeText(text),
                closeSoftKeyboard())
    }

    protected fun performTypeTextNotCloseSoftKeyboard(resourceId: Int, text: String) {
        onView(withId(resourceId)).perform(typeText(text))
    }

    // Performs text insert where the parent necessarily is ScrollView
    //
    // View preconditions:
    // must be a descendant of ScrollView
    // must have visibility set to View.VISIBLE
    protected fun performTypeTextScrollTo(resourceId: Int, text: String) {
        onView(withId(resourceId)).perform(scrollTo(), typeText(text),
                closeSoftKeyboard())
    }

    protected fun performClickScrollTo(resourceId: Int) {
        onView(withId(resourceId)).perform(scrollTo(), click())
    }

    //android.R.string.ok
    protected fun performClickScrollToWithTextOK(resourceId: Int) {
        onView(allOf(withId(resourceId), withText(android.R.string.ok))).perform(scrollTo(), click())
    }

    //android.R.string.cancel
    protected fun performClickScrollToWithTextCancel(resourceId: Int) {
        onView(allOf(withId(resourceId), withText(android.R.string.cancel))).perform(scrollTo(), click())
    }

    protected fun performTypeTextScrollToNotCloseSoftKeyboard(resourceId: Int, text: String) {
        onView(withId(resourceId)).perform(scrollTo(), typeText(text))
    }

    protected fun pressDeviceBack() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressBack()
    }

    protected fun pressHomeBack() {
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation()).pressHome()
    }

    protected fun performPressBack() {
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    protected fun performBackToolbar() {
        onView(allOf(backToolbarMatcher(),
                withParent(withId(R.id.toolbar)),
                isDisplayed())).perform(click())
    }

    //Checks if the view is visible on the screen
    protected fun checkMatchesIsDisplayed(resourceId: Int) {
        onView(withId(resourceId)).check(matches(isDisplayed()))
    }

    protected fun checkMatchesIsDisplayed(resourceId: Int, resourceIdwithParent: Int) {
        onView(allOf(withId(resourceId),
                withParent(withId(resourceIdwithParent)))).check(matches(isDisplayed()))
    }

    //Search on screen, a view that contains the text
    //Checks if the view is visible on the screen
    protected fun checkMatchesIsDisplayed(text: String) {
        onView(withText(text)).check(matches(isDisplayed()))
    }

    /* Checks if the view is visible on the screen
       View preconditions:
       must be a descendant of ScrollView
       must have visibility set to View.VISIBLE */
    protected fun checkMatchesIsDisplayedScrollTo(resourceId: Int) {
        onView(withId(resourceId)).perform(scrollTo(),
                closeSoftKeyboard()).check(matches(isDisplayed()))
    }

    //Checks if the view is not visible on the screen
    protected fun checkMatchesNotIsDisplayed(resourceId: Int) {
        onView(withId(resourceId)).check(matches(not(isDisplayed())))
    }

    fun getString(@StringRes resId: Int): String {
        return activityTestRule.activity.getString(resId)
    }

    companion object {

        private fun backToolbarMatcher(): Matcher<View> {
            return allOf(
                    withParent(withClassName(`is`(Toolbar::class.java.name))),
                    withClassName(anyOf(
                            `is`(ImageButton::class.java.name),
                            `is`(AppCompatImageButton::class.java.name)
                    )))
        }

        protected fun onItemClick(id: Int): BaseViewAction {
            return object : BaseViewAction() {
                override fun perform(uiController: UiController, view: View) {
                    view.findViewById<View>(id).performClick()
                }
            }
        }
    }
}
