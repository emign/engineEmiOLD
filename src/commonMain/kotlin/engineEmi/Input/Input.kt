package engineEmi.Input


import com.soywiz.korev.Key
import com.soywiz.korev.MouseButton
import com.soywiz.korev.MouseEvent
import com.soywiz.korio.async.runBlockingNoSuspensions
import com.soywiz.korma.geom.Point
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


object Keyboard {
    //val keys = Key.values().associate { it to false }.toMutableMap()

    var keys = Key.values().associate { it to false }.toMutableMap()
    val mutex = Mutex()

    suspend fun keyDown(key: Key) {
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
    var event = MouseEvent()
    val mutex = Mutex()
    var position = Point()
    val x: Double
        get() = position.x
    val y
        get() = position.y


    fun buttonDown() {
        runBlockingNoSuspensions {
            mutex.withLock {
                buttons[event.button] = true
            }
        }
    }

    fun buttonReleased() {
        runBlockingNoSuspensions {
            mutex.withLock {
                buttons[event.button] = false
            }
        }

    }

    suspend fun isButtonDown(button: MouseButton): Boolean {
        mutex.withLock {
            return buttons.getOrElse(button) { return false }
        }

    }

    fun movedTo(position: Point) {
        runBlockingNoSuspensions {
            mutex.withLock { this@Mouse.position = position }
        }
    }

    fun receiveEvent(event: MouseEvent) {
        runBlockingNoSuspensions {
            mutex.withLock { this.event = event }
        }


    }
}





