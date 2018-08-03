package my.company.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import my.company.app.*

@Module
interface ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [LauncherModule::class])
    fun contributesLauncherActivity(): LauncherActivity

   @ActivityScope
    @ContributesAndroidInjector(modules = [MainModule::class])
    fun contributesMainActivity(): MainActivity
}
