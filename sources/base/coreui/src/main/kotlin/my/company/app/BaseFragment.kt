package my.company.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.AndroidInjection
import dagger.android.support.DaggerFragment
import my.company.app.component.BaseComponent
import javax.inject.Inject

abstract class BaseFragment<VM : ViewModel> : DaggerFragment() {

    //TODO Rewrite with map
    private lateinit var currentComponent: BaseComponent

    protected fun addComponent(component: BaseComponent) {
        currentComponent = component.apply {
            onCreate()
        }
    }

    protected fun <T : BaseComponent> getComponent() = currentComponent as T

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract val viewModelClass: Class<VM>

    protected val viewModel: VM by lazy { ViewModelProviders.of(this, viewModelFactory).get(viewModelClass) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel
    }
}
