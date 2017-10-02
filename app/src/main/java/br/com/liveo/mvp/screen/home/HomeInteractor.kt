package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.data.source.remote.ApiEndPoint
import br.com.liveo.mvp.model.domain.UserResponse
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by rudsonlima on 9/4/17.
 */
class HomeInteractor @Inject
constructor(private val apiEndPoint: ApiEndPoint) : HomeContract.Interactor {
    override fun fetchUsers(page: Int): Observable<UserResponse> = apiEndPoint.fetchUsers(page)
}
