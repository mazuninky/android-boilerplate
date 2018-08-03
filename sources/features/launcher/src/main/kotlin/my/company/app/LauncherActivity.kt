package my.company.app

import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

class LauncherActivity : EmptyBaseActivity<LauncherViewModel>() {
    override val viewModelClass: Class<LauncherViewModel> = LauncherViewModel::class.java

    override val navigator = Navigator { commands ->
        commands.forEach {
            when (it) {
                is Forward -> {
                    finish()
                    when (it.screenKey) {
                        MAIN_SCREEN -> {

                        }
                    }
                }
            }
        }
    }
}
