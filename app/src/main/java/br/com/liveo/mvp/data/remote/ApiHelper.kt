package br.com.liveo.mvp.data.remote

import br.com.liveo.mvp.model.domain.UserResponse
import io.reactivex.Single

/**
 * Created by rudsonlima on 10/9/17.
 */

class ApiHelper(private val mEndPoint: EndPoint) : EndPointHelper {

    override fun fetchUsers(page: Int): Single<UserResponse> {

        try {
            return if (page != -1) {
                mEndPoint.fetchUsers(page).singleOrError()
            } else Single.error(Exception())

        } catch (error: Exception) {
            return Single.error(error)
        }
    }
}
