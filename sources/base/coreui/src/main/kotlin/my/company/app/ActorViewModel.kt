package my.company.app

import android.arch.lifecycle.ViewModel
import android.support.annotation.CallSuper
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.channels.consumeEach

abstract class ActorViewModel<T> : ViewModel() {
    protected inline fun <T> uiActor(crossinline actionHandler: suspend (T) -> Unit) =
            actor<T>(UI, Channel.CONFLATED) { consumeEach { actionHandler(it) } }

    protected abstract val actor: SendChannel<T>

    fun action(action: T) {
        actor.offer(action)
    }

    @CallSuper
    override fun onCleared() {
        actor.close()
    }
}
