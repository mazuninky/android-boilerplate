package my.company.app.di

import dagger.Component

@Component(modules = [InteractorModule::class], dependencies = [RepositoryProvider::class, MainToolsProvider::class])
interface InteractorComponent: InteractorProvider {
    class Initializer private constructor() {
        companion object {
            fun init(repositoryProvider: RepositoryProvider, mainToolsProvider: MainToolsProvider): InteractorComponent {
                return DaggerInteractorComponent.builder()
                        .repositoryProvider(repositoryProvider)
                        .mainToolsProvider(mainToolsProvider)
                        .build()
            }
        }
    }
}
