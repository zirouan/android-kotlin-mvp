package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.model.domain.UserResponse
import br.com.liveo.mvp.util.scheduler.InjectionScheduler
import io.reactivex.Observable
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as _when

/**
 * This class makes tests for {@link HomePresenter}
 *
 * @author Rudson Lima
 * @since 09/24/17
 */
@RunWith(JUnit4::class)
class HomePresenterTest {

    @Mock
    lateinit var mPresenter: HomePresenter

    @Mock
    var mInteractor: HomeInteractor? = null

    @Mock
    var mView: HomeContract.View? = null

    @Mock
    var mUserResponse: UserResponse? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        _when(mInteractor!!.fetchUsers(2)).thenReturn(Observable.just<UserResponse>(mUserResponse))

        mPresenter = HomePresenter(InjectionScheduler.schedulerProvider());
        mPresenter.attach(this.mView!!)
    }

    @After
    fun tearDown() {
        mPresenter.detachView()
    }

    @Test
    fun fetchUsers_sucess() {
        mInteractor!!.fetchUsers(2)
        verify(mInteractor!!, times(1)).fetchUsers(2)
    }

    @Test
    fun fetchUsers_returningSuccess_forView() {
        mView!!.onLoading(true)
        mInteractor!!.fetchUsers(2)
        mView!!.onLoading(false)
        
        verify(mView!!, times(1)).onLoading(true)
        verify(mView!!, times(1)).onLoading(false)

        mView!!.onUserResponse(mUserResponse!!)
        verify(mView!!, times(1)).onUserResponse(mUserResponse!!)
        verify(mView!!, never()).onError("Error")
    }

    @Test
    fun fetchUsers_returningFailing_forView() {
        val throwable = Throwable()
        _when(mInteractor!!.fetchUsers(2)).thenReturn(Observable.error(throwable))

        mInteractor!!.fetchUsers(2)

        mView!!.onLoading(false)
        mView!!.onError(throwable.message)

        verify(mView!!, times(1)).onLoading(false)
        verify(mView!!, times(1)).onError(throwable.message)
        verify(mView!!, never()).onUserResponse(mUserResponse!!)
    }

    @Test
    fun attach_isNotNUll_sucess() {
        Assert.assertNotNull(mPresenter.getView())
    }

    @Test
    fun detachView_isNUll_sucess() {
        Assert.assertNotNull(mPresenter.getView())

        mPresenter.detachView()
        Assert.assertNull(mPresenter.getView())
    }
}
