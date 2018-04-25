package br.com.liveo.mvp.base

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by rudsonlima on 9/16/17.
 */

abstract class BaseAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    protected var dataList: List<T>? = null

    private val onItemClick = PublishSubject.create<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            onCreateViewHolderBase(parent, viewType)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        this.onBindViewHolderBase(holder, position)

        holder.itemView?.setOnClickListener {
            val index = holder.adapterPosition
            (dataList?.get(index))?.let {
                onItemClick.onNext(it)
            }
        }
    }

    abstract fun onCreateViewHolderBase(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindViewHolderBase(holder: RecyclerView.ViewHolder?, position: Int)

    override fun getItemCount(): Int {
        dataList?.size?.let {
            return it
        }

        return 0
    }

    fun getItem(index: Int): T {
        dataList?.get(index)?.let {
            return it
        }

        throw IllegalArgumentException("Item with index $index doesn't exist, dataSet is $dataList")
    }

    fun itemClick(): Observable<T> = onItemClick
}
