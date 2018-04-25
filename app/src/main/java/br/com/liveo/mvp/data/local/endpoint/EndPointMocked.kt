package br.com.liveo.mvp.data.local.endpoint

import br.com.liveo.mvp.data.remote.EndPoint
import br.com.liveo.mvp.model.domain.UserResponse
import com.google.gson.Gson
import io.reactivex.Observable

/**
 * Created by rudsonlima on 8/29/17.
 */

class EndPointMocked : EndPoint {

    override fun fetchUsers(page: Int): Observable<UserResponse> {
        val json = if (page != -1) EndPointJson.USER_JSON_SUCCESS else
            EndPointJson.USER_JSON_FAIL

        return Observable.just<UserResponse>(Gson().fromJson<UserResponse>(
                (json), UserResponse::class.java))
    }
}
