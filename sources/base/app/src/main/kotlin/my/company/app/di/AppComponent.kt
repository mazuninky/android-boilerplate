package my.company.app.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import my.company.app.MyApplication
import javax.inject.Singleton

@Component(dependencies = [
    MainToolsProvider::class,
    RepositoryProvider::class,
    InteractorProvider::class
], modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    RouterModule::class,
    ViewModelModule::class
])
@Singleton
interface AppComponent : AndroidInjector<MyApplication> {
    class Initializer private constructor() {
        companion object {

            fun init(app: MyApplication): AppComponent {
                val mainToolsProvider = MainToolsComponent.Initializer
                        .init(app)

                val repositoryProvider = RepositoryComponent.Initializer.init(mainToolsProvider)

                val interactorProvider = InteractorComponent.Initializer.init(repositoryProvider, mainToolsProvider)

                return DaggerAppComponent.builder()
                        .mainToolsProvider(mainToolsProvider)
                        .repositoryProvider(repositoryProvider)
                        .interactorProvider(interactorProvider)
                        .build()
            }
        }
    }
}
