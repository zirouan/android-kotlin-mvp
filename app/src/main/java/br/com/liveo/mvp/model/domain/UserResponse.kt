package br.com.liveo.mvp.model.domain

import br.com.liveo.mvp.model.User
import com.google.gson.annotations.SerializedName

/**
 * Created by rudsonlima on 8/29/17.
 */

class UserResponse(

        var page: Int,
        var total: Int,

        @SerializedName("per_page")
        var perPage: Int,

        @SerializedName("total_pages")
        var totalPages: Int,

        @SerializedName("data")
        var list: List<User>) {
}
