package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.data.source.remote.ApiEndPoint
import br.com.liveo.mvp.model.domain.UserResponse
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as _when

/**
 * This class makes tests for [HomeInteractor]
 *
 * @author Rudson Lima
 * @since 09/24/17
 */

class HomeInteractorTest {

    val PAGE: Int? = 2

    @Mock
    var mApiEndPoint: ApiEndPoint? = null

    @Mock
    lateinit var mUserResponse: UserResponse

    @Mock
    lateinit var mInteractor: HomeContract.Interactor

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        mInteractor = HomeInteractor(mApiEndPoint!!)
        _when(mInteractor.fetchUsers(PAGE!!)).thenReturn(Single.just(mUserResponse))
    }

    @Test
    fun fetchUsers_sucess() {
        mInteractor.fetchUsers(2)
        verify(mApiEndPoint!!).fetchUsers(2)
    }

    @Test
    fun fetchUsers_noErros_sucess() {
        val subscriber = TestObserver.create<UserResponse>()
        mApiEndPoint!!.fetchUsers(2).subscribe(subscriber)
        subscriber.onNext(mUserResponse)
        subscriber.assertNoErrors()
        subscriber.assertComplete()
    }
}
