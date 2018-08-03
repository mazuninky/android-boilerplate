package my.company.app.di

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class RouterModule {
    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun providesRouter(): Router = cicerone.router

    @Provides
    fun providesNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
