package com.hiden.movies.presentation

import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.DisposableContainer
import javax.inject.Inject


abstract class AppActivity(@LayoutRes contentLayoutId: Int = 0) : DaggerAppCompatActivity(contentLayoutId) {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory


    private val compositeDisposable = CompositeDisposable()

    protected val disposableContainer: DisposableContainer get() = compositeDisposable

    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        checkNotNull(application) {
            "Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call."
        }
        return viewModelFactory
    }



    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}