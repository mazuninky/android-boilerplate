package my.company.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.CallSuper
import dagger.android.support.DaggerAppCompatActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

abstract class EmptyBaseActivity<VM : ViewModel> : DaggerAppCompatActivity() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract val viewModelClass: Class<VM>

    protected val viewModel: VM by lazy { ViewModelProviders.of(this, viewModelFactory).get(viewModelClass) }

    @Inject
    protected lateinit var navigationHolder: NavigatorHolder

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        navigationHolder.setNavigator(navigator)
    }

    @CallSuper
    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

    protected abstract val navigator: Navigator
}
