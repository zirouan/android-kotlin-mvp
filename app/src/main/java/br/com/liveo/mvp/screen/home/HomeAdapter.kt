package br.com.liveo.mvp.screen.home

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.liveo.mvp.BR
import br.com.liveo.mvp.R
import br.com.liveo.mvp.base.BaseAdapter
import br.com.liveo.mvp.model.User
import br.com.liveo.mvp.model.domain.UserResponse

/**
 * Created by rudsonlima on 8/29/17.
 */

class HomeAdapter(userResponse: UserResponse) : BaseAdapter<User>() {

    init {
        this.dataList = userResponse.list
    }

    override fun onCreateViewHolderBase(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return HomeViewHolder(LayoutInflater.from(parent?.context).inflate(
                R.layout.activity_home_item, parent, false))
    }

    override fun onBindViewHolderBase(holder: RecyclerView.ViewHolder?, position: Int) {
        val user = dataList?.get(position)

        (holder as HomeViewHolder).binding?.setVariable(BR.user, user)
        holder.binding?.executePendingBindings()

    }
}
