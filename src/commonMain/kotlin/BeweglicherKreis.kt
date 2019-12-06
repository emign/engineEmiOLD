import com.soywiz.korev.Key
import com.soywiz.korev.KeyEvent
import com.soywiz.korev.MouseEvent
import com.soywiz.korim.color.Colors
import com.soywiz.korim.color.RGBA
import engineEmi.CanvasElements.Kreis

class BeweglicherKreis(radius: Int = 50, x: Int = 100, y: Int = 100, fuellFarbe: RGBA = Colors.ALICEBLUE) :
    Kreis(radius = radius, x = x, y = y, fuellFarbe = fuellFarbe) {
    override fun reactToKeyboardInput(key: Key, type: KeyEvent.Type) {
        println("gedrückt")
        when (key) {
            Key.LEFT -> x -= 10
            Key.RIGHT -> x += 10
            Key.DOWN -> y += 10
            Key.UP -> y -= 10
        }
    }

    override fun reactToKeyboardInputs(keys: Collection<Key>, type: KeyEvent.Type) {
        keys.forEach { reactToKeyboardInput(it, type) }
    }

    override fun reactToMouseInput(type: MouseEvent.Type) {
        println("$type")
    }
}