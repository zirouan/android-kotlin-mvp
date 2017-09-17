package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.base.BaseView
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

/**
 * Created by rudsonlima on 8/29/17.
 */

class HomeContractTest {

    private var entryClassView: Class<*>? = null
    private var entryClassPresenter: Class<*>? = null

    @Before
    fun setUp() {
        this.entryClassView = HomeContract.View::class.java
        this.entryClassPresenter = HomeContract.Presenter::class.java
    }

    @Test
    fun homeContractView_isBaseView() {
        assertTrue(BaseView::class.java.isAssignableFrom(this.entryClassView!!))
    }

    @Test
    fun homeContractView_isHomeContractView() {
        assertTrue(HomeContract.View::class.java.isAssignableFrom(this.entryClassView!!))
    }

    @Test
    fun homeContractPresenter_isHomeContractPresenter() {
        assertTrue(HomeContract.Presenter::class.java.isAssignableFrom(this.entryClassPresenter!!))
    }
}
