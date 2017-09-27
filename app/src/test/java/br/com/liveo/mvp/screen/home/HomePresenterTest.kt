package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.model.domain.UserResponse
import br.com.liveo.mvp.util.scheduler.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
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

    lateinit var mTestScheduler: TestScheduler
    lateinit var mPresenter: HomeContract.Presenter

    @Mock
    var mView: HomeContract.View? = null

    @Mock
    val mUserResponse: UserResponse? = null

    @Mock
    val mInteractor: HomeContract.Interactor? = null

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        _when(mView!!.page).thenReturn(2)
        _when(mInteractor!!.fetchUsers(2)).thenReturn(Single.just(mUserResponse!!))

        mTestScheduler = TestScheduler()
        mPresenter = HomePresenter(mInteractor, TestSchedulerProvider(mTestScheduler))
        mPresenter.attach(this.mView!!)
    }

    @After
    fun tearDown() {
        mPresenter.detachView()
    }

    @Test
    fun fetchUsers_sucess() {
        mPresenter.fetchUsers()
        verify(mInteractor!!, times(1)).fetchUsers(2)
    }

    @Test
    fun fetchUsers_returning_loadingSuccess_forView() {
        mPresenter.fetchUsers()

        verify(this.mView!!, times(1)).page
        verify(this.mView!!, times(1)).onLoading(true)

        mTestScheduler.triggerActions()

        verify(this.mView!!, times(1)).onLoading(false)
    }

    @Test
    fun fetchUsers_returningSuccess_forView() {
        mPresenter.fetchUsers()

        mTestScheduler.triggerActions()

        verify(this.mView!!, times(1)).onUserResponse(mUserResponse!!)
        verify(this.mView!!, never()).onError(null)
    }

    @Test
    fun fetchUsers_returningFailing_forView() {
        val throwable = Throwable()
        _when(mInteractor!!.fetchUsers(2)).thenReturn(Single.error(throwable))

        mPresenter.fetchUsers()

        mTestScheduler.triggerActions()

        verify(mView!!).onError(throwable)
        verify(this.mView!!, times(1)).onLoading(false)
        verify(this.mView!!, never()).onUserResponse(mUserResponse!!)
    }

    @Test
    fun attach_isNotNull_sucess() {
        Assert.assertNotNull(mPresenter.getView())
    }

    @Test
    fun detachView_isNull_sucess() {
        Assert.assertNotNull(mPresenter.getView())

        mPresenter.detachView()
        Assert.assertNull(mPresenter.getView())
    }
}
