package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.model.domain.UserResponse
import br.com.liveo.mvp.data.source.remote.ApiEndPoint
import io.reactivex.Observable

/**
 * Created by rudsonlima on 9/4/17.
 */
open class HomeInteractor(private val apiEndPoint: ApiEndPoint) : HomeContract.Interactor {
    override fun fetchUsers(page: Int): Observable<UserResponse> = apiEndPoint.fetchUsers(page)
}
