package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.base.BaseView
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by rudsonlima on 8/29/17.
 */

class HomeActivityTest {

    private var entryClass: Class<*>? = null

    @Before
    fun setUp() {
        this.entryClass = HomeActivity::class.java
    }

    @Test
    fun homeView_isBaseView() {
        assertTrue(BaseView::class.java.isAssignableFrom(entryClass!!))
    }

    @Test
    fun homeView_isHomeContractView() {
        assertTrue(HomeContract.View::class.java.isAssignableFrom(entryClass!!))
    }
}
