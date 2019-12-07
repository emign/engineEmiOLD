package engineEmi.Input


import com.soywiz.korev.Key
import com.soywiz.korev.MouseButton
import com.soywiz.korma.geom.Point
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


object Keyboard {
    //val keys = Key.values().associate { it to false }.toMutableMap()

    var keys = Key.values().associate { it to false }.toMutableMap()
    val mutex = Mutex()
    suspend fun keyPressed(key: Key) {
        mutex.withLock {
            keys[key] = true
        }
    }

    suspend fun keyReleased(key: Key) {
        mutex.withLock {
            if (keys.contains(key)) {
                keys[key] = false
            }

        }
    }

    fun isKeyDown(key: Key): Boolean {
        return keys.getOrElse(key) { return false }
    }

}

object Mouse {
    var buttons = MouseButton.values().associate { it to false }.toMutableMap()
    var position = Point()
    val x
        get() = position.x
    val y
        get() = position.y


    fun buttonPressed(button: MouseButton) {
        buttons[button] = true
    }

    suspend fun buttonReleased(button: MouseButton) {

        buttons[button] = false

    }

    fun isButtonDown(button: MouseButton): Boolean {
        return buttons.getOrElse(button) { return false }
    }

    fun movedTo(position: Point) {
        this.position = position
    }
}




