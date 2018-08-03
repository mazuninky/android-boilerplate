package my.company.app

import android.arch.lifecycle.ViewModel
import android.content.Context
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class LauncherViewModel @Inject constructor(router: Router) : ViewModel() {

    init {
        launch(UI) {router.navigateTo(MAIN_SCREEN)}
    }
}
