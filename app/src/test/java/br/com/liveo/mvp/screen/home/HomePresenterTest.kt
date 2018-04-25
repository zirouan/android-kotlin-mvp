package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.data.scheduler.TestSchedulerProvider
import br.com.liveo.mvp.model.domain.UserResponse
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
    lateinit var mUserResponse: UserResponse

    @Mock
    lateinit var mRepository: HomeContract.Repository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        this.mView?.let {

            _when(mView?.page).thenReturn(2)
            _when(mRepository.fetchUsers(2)).thenReturn(Single.just(mUserResponse))

            mTestScheduler = TestScheduler()
            mPresenter = HomePresenter(mRepository, TestSchedulerProvider(mTestScheduler))
            mPresenter.attachView(it)
        }
    }

    @After
    fun tearDown() {
        mPresenter.detachView()
    }

    @Test
    fun fetchUsers_sucess() {
        mPresenter.fetchUsers()
        verify(mRepository, times(1)).fetchUsers(2)
    }

    @Test
    fun fetchUsers_returning_loadingSuccess_forView() {
        this.mView?.let {
            mPresenter.fetchUsers()

            verify(it, times(1)).page
            verify(it, times(1)).onLoading(true)

            mTestScheduler.triggerActions()

            verify(it, times(1)).onLoading(false)
        }
    }

    @Test
    fun fetchUsers_returningSuccess_forView() {
        this.mView?.let {
            mPresenter.fetchUsers()

            mTestScheduler.triggerActions()

            verify(it, times(1)).onUserResponse(mUserResponse)
            verify(it, never()).onError(null)
        }
    }

    @Test
    fun fetchUsers_returningFailing_forView() {
        this.mView?.let {
            val throwable = Throwable()
            _when(mRepository.fetchUsers(2)).thenReturn(Single.error(throwable))

            mPresenter.fetchUsers()

            mTestScheduler.triggerActions()

            verify(it).onError(throwable)
            verify(it, times(1)).onLoading(false)
            verify(it, never()).onUserResponse(mUserResponse)
        }
    }

    @Test
    fun attach_isNotNull_sucess() {
        Assert.assertNotNull(mPresenter.view)
    }

    @Test
    fun detachView_isNull_sucess() {
        Assert.assertNotNull(mPresenter.view)

        mPresenter.detachView()
        Assert.assertNull(mPresenter.view)
    }
}
