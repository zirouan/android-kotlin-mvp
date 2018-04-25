package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.data.remote.endpoint.EndPointHelper
import br.com.liveo.mvp.model.domain.UserResponse
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by rudsonlima on 9/4/17.
 */
class HomeRepository @Inject
constructor(private val endPointHelper: EndPointHelper) : HomeContract.Repository {
    override fun fetchUsers(page: Int): Single<UserResponse> = endPointHelper.fetchUsers(page)
}
