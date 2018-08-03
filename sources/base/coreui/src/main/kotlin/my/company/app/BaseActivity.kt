package my.company.app

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.annotation.LayoutRes

abstract class BaseActivity<VM : ViewModel> : EmptyBaseActivity<VM>() {
    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
    }
}
