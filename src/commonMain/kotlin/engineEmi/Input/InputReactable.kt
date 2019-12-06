package engineEmi.Input

import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korev.MouseEvent
import com.soywiz.korge.input.GamePadEvents

interface InputReactable {
    fun reactToKeyboardInput(key: Key, type: KeyEvent.Type)
    fun reactToKeyboardInputs(keys: Collection<Key>, type: KeyEvent.Type)
    fun reactToMouseInput(type: MouseEvent.Type)
    fun reactToGamepadInput(type: GamePadEvents)
}

