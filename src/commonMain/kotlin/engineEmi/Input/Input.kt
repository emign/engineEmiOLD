package engineEmi.Input


import com.soywiz.korev.Key
import com.soywiz.korev.MouseEvent
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


object Keyboard {
    var keys = Key.values().associate { it to false }.toMutableMap()
    val mutex = Mutex()

    /**
     * Wird nur intern benötigt
     * @param key Key
     */
    suspend fun keyDown(key: Key) {
        mutex.withLock {
            keys[key] = true
        }
    }

    /**
     * Wird nur intern benötigt
     * @param key Key
     */
    suspend fun keyReleased(key: Key) {
        mutex.withLock {
            if (keys.contains(key)) {
                keys[key] = false
            }

        }
    }

    /**
     * Gibt true zurück, wenn die übergebene Taste gerade gedrückt wird.
     * @param key Key
     * @return Boolean
     */
    fun isKeyDown(key: Key): Boolean {
        return keys.getOrElse(key) { return false }
    }

}

interface MouseEventReacteable {
    fun reactToMouseEvent(event: MouseEvent)
}







