package br.com.liveo.mvp.model.domain

import br.com.liveo.mvp.model.User
import com.google.gson.annotations.SerializedName

/**
 * Created by rudsonlima on 8/29/17.
 */

class UserResponse {

    var page: Int = 0

    var total: Int = 0

    @SerializedName("per_page")
    var perPage: Int = 0

    @SerializedName("total_pages")
    var totalPages: Int = 0

    @SerializedName("data")
    var list: List<User>? = null
}
