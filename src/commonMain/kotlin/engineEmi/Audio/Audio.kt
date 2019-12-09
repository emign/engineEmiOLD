package engineEmi.Audio

import com.soywiz.korau.sound.await
import com.soywiz.korau.sound.readNativeSound
import com.soywiz.korio.file.std.resourcesVfs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Liest Audio Dateien (WAV, MP3, OGG) aus dem resources Ordner und spielt diese ab
 * @property filePath Pfad inkl Dateiname. Relativ zum resources Ordner
 * @property streaming When true: Audio wird nicht komplett in den Speicher geladen, sondern gestreamed. Geeignet für Hintergrundmusik
 * @constructor
 */
class Audio(val filePath: String = "", val streaming: Boolean = false) {
    fun play() {
        CoroutineScope(Dispatchers.Default).launch {
            val sound = resourcesVfs[filePath].readNativeSound(streaming)
            val channel = sound.play().await()
        }
    }
}