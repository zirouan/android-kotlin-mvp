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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return onCreateViewHolderBase(parent, viewType)
    }

    abstract fun onCreateViewHolderBase(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        this.onBindViewHolderBase(holder, position)

        holder.itemView.setOnClickListener { onItemClick.onNext(dataList!![holder.adapterPosition]) }
    }

    abstract fun onBindViewHolderBase(holder: RecyclerView.ViewHolder, position: Int)

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int {
        return if (dataList != null && dataList!!.size > 0) dataList!!.size else 0
    }

    fun getItem(index: Int): T {
        return if (dataList != null && dataList!![index] != null) {
            dataList!![index]
        } else {
            throw IllegalArgumentException("Item with index $index doesn't exist, dataSet is $dataList")
        }
    }

    fun observableItemClick(): Observable<T> {
        return onItemClick
    }
}
