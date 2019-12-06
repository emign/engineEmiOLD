package engineEmi.Input

import com.soywiz.korev.*
import com.soywiz.korge.input.GamePadEvents

enum class Input {
    MOUSE, KEYBOARD, GAMEPAD, NONE
}

enum class InputEvent {
    ONDOWN, ONUP, ONCLICK
}

class LastKeyEvent(val event: KeyEvent, val timestamp: Int)
class KeyboardAction(
    val key: Key,
    val eventType: KeyEvent.Type = KeyEvent.Type.DOWN,
    val target: InputReactable,
    var lastInputThreshold: Int = 100
) : InputAction() {
    val inputType = KeyEvent.Type.DOWN
    var pressedKey = Key.UNKNOWN
    lateinit var lastEvent: LastKeyEvent

    override fun sendKeyEvent(event: KeyEvent) {
        pressedKey = event.key
        lastEvent = LastKeyEvent(event, 100)
        if (pressedKey == this.key && event.type == eventType) {
            // if (lastEvent.isInitialized() && lastEvent.timestamp <= now-thresshold){
            target.reactToKeyboardInputs(listOf(lastEvent.event.key), eventType)
            // } else {
            target.reactToKeyboardInput(pressedKey, inputType)
        }


    }

}

class MouseAction(
    val button: MouseButton,
    val eventType: MouseEvent.Type = MouseEvent.Type.CLICK,
    val target: InputReactable
) : InputAction() {
    val inputType = MouseEvent.Type.CLICK
    var pressedButton = MouseButton.LEFT

    override fun sendMouseEvent(mouseEvents: MouseEvents) {

        runAction()
    }

    fun runAction() {
        target.reactToMouseInput(inputType)
    }


}


abstract class InputAction {
    open fun sendKeyEvent(keyEvent: KeyEvent) {}
    open fun sendMouseEvent(mouseEvents: MouseEvents) {}
    open fun sendGamepadEvent(gamepadEvents: GamePadEvents) {}

    fun sendEvent(event: Any) {

        when (event) {
            is KeyEvent -> sendKeyEvent(event)
            is MouseEvents -> {
                println("Triggered"); sendMouseEvent(event)
            }
            is GamePadEvents -> sendGamepadEvent(event)

        }
    }
}

