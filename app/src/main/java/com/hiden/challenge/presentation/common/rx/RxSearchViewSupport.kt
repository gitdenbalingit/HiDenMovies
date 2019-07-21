package com.fotoku.mobile.libs.rx.observable

import androidx.appcompat.widget.SearchView
import io.reactivex.Observable

object RxSearchViewSupport {
  @JvmStatic
  fun changes(searchView: SearchView): Observable<out String> = ViewQueryChangeObservable(
      searchView
  )
}