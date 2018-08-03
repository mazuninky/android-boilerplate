package my.company.app.di

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module

import javax.inject.Singleton

@Module
abstract class ToolsModule {

}

@Singleton
@Component(modules = [ToolsModule::class])
interface MainToolsComponent : MainToolsProvider {

    @Component.Builder
    interface Builder {
        fun build(): MainToolsComponent
        @BindsInstance
        fun context(context: Context): Builder
    }

    class Initializer private constructor() {
        companion object {

            fun init(app: Application): MainToolsProvider =
                    DaggerMainToolsComponent.builder()
                            .context(app.applicationContext)
                            .build()
        }
    }

}
