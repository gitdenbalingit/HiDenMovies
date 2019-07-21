package com.hiden.challenge.presentation.common.rx

import com.google.android.material.appbar.AppBarLayout
import com.hiden.challenge.presentation.util.checkMainThread
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable

internal class ViewAlphaOnSubscribe(private val mAppbarLayout: AppBarLayout) : Observable<Float>() {


    override fun subscribeActual(observer: Observer<in Float>) {
        if (!checkMainThread(observer)) {
            return
        }

        val listener = Listener(mAppbarLayout,observer)
        observer.onSubscribe(listener)
        mAppbarLayout.addOnOffsetChangedListener(listener)
    }

    internal class Listener(
        private val appBarLayout: AppBarLayout,
        private val observer: Observer<in Float>
    ): MainThreadDisposable(), AppBarLayout.OnOffsetChangedListener {
        override fun onDispose() {
            appBarLayout.removeOnOffsetChangedListener(this)
        }

        override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
            observer.onNext(Math.abs(verticalOffset / appBarLayout.totalScrollRange.toFloat()))
        }
    }

}