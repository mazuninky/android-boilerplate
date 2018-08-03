package my.company.app.di

import dagger.Component

@Component(dependencies = [MainToolsProvider::class],modules=[RepositoryModule::class])
interface RepositoryComponent: RepositoryProvider {
    class Initializer private constructor() {
        companion object {
            fun init(mainToolsProvider: MainToolsProvider): RepositoryComponent {
                return DaggerRepositoryComponent.builder()
                        .mainToolsProvider(mainToolsProvider)
                        .build()
            }
        }
    }
}
