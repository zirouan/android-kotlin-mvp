package br.com.liveo.mvp.screen.home


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import br.com.liveo.mvp.R
import br.com.liveo.mvp.base.BaseActivityTestRule
import org.hamcrest.Matchers.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This class makes activity for [HomeActivity]
 *
 * @author Rudson Lima
 * @since 10/02/17
 */

@RunWith(AndroidJUnit4::class)
class HomeActivityTest() : BaseActivityTestRule() {

    @get:Rule
    var mActivityTestRule: ActivityTestRule<HomeActivity> = ActivityTestRule(
            HomeActivity::class.java, true, false)

    override val activityTestRule: ActivityTestRule<*>
        get() = mActivityTestRule

    @Before
    fun setUp() {
        this.onInitHelperTestModule()
    }

    @Test
    fun onItemClick_zeroSuccess() {

        onView(allOf<View>(withId(R.id.recycler_view),
                withParent(withId(R.id.swipe_container)),
                isDisplayed())).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun onItemClick_oneSuccess() {
        onView(allOf<View>(withId(R.id.recycler_view),
                withParent(withId(R.id.swipe_container)),
                isDisplayed())).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
    }

    @Test
    fun onItemClick_twoSuccess() {
        onView(allOf<View>(withId(R.id.recycler_view),
                withParent(withId(R.id.swipe_container)),
                isDisplayed())).perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(2, click()))
    }
}
