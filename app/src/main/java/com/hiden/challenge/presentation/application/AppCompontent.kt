package com.hiden.challenge.presentation.application

import com.hiden.challenge.data.di.NetworkModule
import com.hiden.challenge.presentation.di.PerApplication
import com.hiden.challenge.presentation.di.modules.ActivityBindingModule
import com.hiden.challenge.presentation.di.modules.ApplicationModule
import com.hiden.challenge.presentation.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule

@Component(
    modules = [
        (ApplicationModule::class),
        (NetworkModule::class),
        (ActivityBindingModule::class),
        (ViewModelModule::class),
        (AndroidInjectionModule::class),
        (AndroidSupportInjectionModule::class)]
)
@PerApplication
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(hiDenChallengeApplication: HiDenChallengeApplication): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: HiDenChallengeApplication)
}
