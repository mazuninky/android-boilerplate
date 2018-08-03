package  my.company.app

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import ru.terrakok.cicerone.android.SupportAppNavigator

class MainActivity : BaseActivity<MainViewModel>() {
    override val layoutId: Int = R.layout.activity_main
    override val viewModelClass: Class<MainViewModel> = MainViewModel::class.java

    override val navigator = object : SupportAppNavigator(this, R.id.frameLayout) {
        override fun createActivityIntent(context: Context, screenKey: String, data: Any?): Intent? {
            return null
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? = when (screenKey) {
            else -> null
        }
    }

}
