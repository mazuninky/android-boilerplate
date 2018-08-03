package my.company.app

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import my.company.app.di.AppComponent

class MyApplication : DaggerApplication() {
    private val appComponent by lazy { AppComponent.Initializer.init(this) }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent
}
