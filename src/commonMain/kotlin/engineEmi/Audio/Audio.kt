package engineEmi.Audio

import com.soywiz.korau.sound.NativeSound
import com.soywiz.korau.sound.await
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Job


typealias SuspendedValue<T> = CompletableDeferred<T>

fun <T> SuspendedValue(parent: Job? = null): SuspendedValue<T> = CompletableDeferred(parent)
fun <T> SuspendedValue<T>.set(t: T) = this.complete(t)
suspend fun <T> SuspendedValue<T>.get(): T = this.await()

class Audio(val pathToMp3: String, val soundType: SoundType = SoundType.EFFECT) {
    var sound = SuspendedValue<NativeSound>()


    suspend fun play() {
        val channel = sound.get().play()
        channel.await()
    }


}


enum class SoundType {
    EFFECT, MUSIC
}