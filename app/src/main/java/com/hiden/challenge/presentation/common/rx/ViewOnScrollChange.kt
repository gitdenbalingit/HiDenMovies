package com.hiden.challenge.presentation.common.rx

import androidx.recyclerview.widget.RecyclerView
import com.hiden.challenge.presentation.util.checkMainThread
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

internal class ViewOnScrollChange(private val mRecyclerView: RecyclerView) : Observable<Int>() {


    override fun subscribeActual(observer: Observer<in Int>) {
        if (!checkMainThread(observer)) {
            return
        }

        val listener = Listener(mRecyclerView,observer)
        observer.onSubscribe(listener.disposable)
        mRecyclerView.addOnScrollListener(listener)
    }

    internal class Listener(
        private val recyclerView: RecyclerView,
        private val observer: Observer<in Int>
    ):  RecyclerView.OnScrollListener() {

        val disposable = object: MainThreadDisposable(){
            override fun onDispose() {
                recyclerView.removeOnScrollListener(this@Listener)
            }

        }



        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            observer.onNext(dx)
        }


    }



}