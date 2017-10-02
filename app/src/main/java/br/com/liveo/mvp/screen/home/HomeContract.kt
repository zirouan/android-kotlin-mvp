package br.com.liveo.mvp.screen.home

import br.com.liveo.mvp.base.BaseView
import br.com.liveo.mvp.main.MainPresenter
import br.com.liveo.mvp.model.domain.UserResponse
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by rudsonlima on 8/29/17.
 */

interface HomeContract {

    interface View : BaseView<Presenter> {
        val page: Int

        fun onUserResponse(userResponse: UserResponse)
    }

    interface Presenter : MainPresenter<View> {
        fun fetchUsers()
    }

    interface Interactor {
        fun fetchUsers(page: Int): Observable<UserResponse>
    }
}
